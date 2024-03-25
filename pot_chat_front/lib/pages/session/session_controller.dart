import 'package:get/get.dart';
import 'package:logger/logger.dart';
import 'package:pot_chat_front/http/http_code.dart';
import 'package:pot_chat_front/models/common_resp.dart';
import 'package:pot_chat_front/models/create_session.dart';
import 'package:pot_chat_front/models/list_session.dart';
import 'package:pot_chat_front/models/session.dart';
import 'package:pot_chat_front/routes/app_pages.dart';
import 'package:pot_chat_front/service/chat_service.dart';
import 'package:pot_chat_front/service/user_service.dart';
import 'package:sp_util/sp_util.dart';

class SessionController extends GetxController {
  final Logger _logger = Logger();
  // final ImagePicker _picker = Get.find<ImagePicker>();
  final UserService _userService = Get.find<UserService>();
  final ChatService _chatService = Get.find<ChatService>();

  final RxList<Session> _sessions = List<Session>.from([]).obs;

  set sessions(val) => _sessions.value = val;
  List<Session> get sessions => _sessions;

  void logout() async {
    await _userService.logout();
    SpUtil.clear();
    Get.offAllNamed(AppRoutes.init);
  }

  // void addFace() async {
  //   final cameraImages = await _picker.pickImage(source: ImageSource.camera);
  //   if (cameraImages == null) {
  //     Get.snackbar("拍摄人脸不能为空", "");
  //     return;
  //   }
  //   final image = await cameraImages.readAsBytes();
  // }

  Future<String?> create() async {
    CommonResp? commonResp = await _chatService.create();
    if (commonResp == null || commonResp.code != HttpCode.success) {
      _logger.e('创建对话失败, ${commonResp?.message ?? '未知原因'}');
      Get.snackbar('创建对话失败', commonResp?.message ?? '网络异常');
      return null;
    }

    CreateSessionResp createSessionResp =
        CreateSessionResp.fromJson(commonResp.data);

    _sessions.add(Session(
      sessionId: createSessionResp.sessionId,
      description: "新建聊天",
    ));
    _logger.i("createSessionResp: $createSessionResp");
    return createSessionResp.sessionId;
  }

  // void info(String sessionId) {
  //   // todo 获取详情, 跳转页面
  // }

  list() async {
    CommonResp? commonResp = await _chatService.list();
    try {
      _logger.i("commonResp: $commonResp");
      if (commonResp == null || commonResp.code != HttpCode.success) {
        _logger.e('获取历史对话列表失败, ${commonResp?.message ?? '未知原因'}');
        Get.snackbar('获取历史对话列表失败', commonResp?.message ?? '网络异常');
        return;
      }

      _logger.i(commonResp.data);
      var list = commonResp.data as List;
      _sessions.value = list.map((i) {
        ListSessionResp temp = ListSessionResp.fromJson(i);
        return Session(
          sessionId: temp.sessionId,
          description: temp.description ?? "新建聊天",
          updateTime: temp.updateTime,
        );
      }).toList();
    } catch (e) {
      _logger.e('获取历史对话列表失败, $e');
    }
  }

  delete(String id) async {
    CommonResp? commonResp = await _chatService.delete(id);
    if (commonResp == null || commonResp.code != HttpCode.success) {
      _logger.e('删除历史对话失败, ${commonResp?.message ?? '未知原因'}');
      Get.snackbar('删除历史对话失败', commonResp?.message ?? '网络异常');
      return;
    }

    Get.snackbar('删除成功', '');
    _sessions.removeWhere((e) => e.sessionId == id);
    return;
  }
}
