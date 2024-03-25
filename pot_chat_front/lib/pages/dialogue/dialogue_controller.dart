import 'package:flutter_client_sse/constants/sse_request_type_enum.dart';
import 'package:flutter_client_sse/flutter_client_sse.dart';
import 'package:get/get.dart';
import 'package:logger/logger.dart';
import 'package:pot_chat_front/constants/constants.dart';
import 'package:pot_chat_front/http/http_code.dart';
import 'package:pot_chat_front/http/request_apis.dart';
import 'package:pot_chat_front/models/chat.dart';
import 'package:pot_chat_front/models/common_resp.dart';
import 'package:pot_chat_front/models/info_session.dart';
import 'package:pot_chat_front/models/roleContent.dart';
import 'package:pot_chat_front/models/user.dart';
import 'package:pot_chat_front/service/chat_service.dart';
import 'package:sp_util/sp_util.dart';

class DialogueController extends GetxController {
  static final Logger _logger = Logger();
  static final ChatService _chatService = Get.find<ChatService>();

  final String _nickname =
      User.fromJson(Map.from(SpUtil.getObject(Constants.user)!)).nickname!;

  String get nickname => _nickname;

  final RxList<RoleContent> _history = List<RoleContent>.from([]).obs;

  set history(List<RoleContent> value) => _history.value = value;
  List<RoleContent> get history => _history;

  Future<void> info(String sessionId) async {
    CommonResp? commonResp = await _chatService.info(sessionId);
    if (commonResp == null || commonResp.code != HttpCode.success) {
      _logger.e('获取对话历史失败, ${commonResp?.message ?? '未知原因'}');
      Get.snackbar('获取对话历史失败', commonResp?.message ?? '网络异常');
      return;
    }

    SessionInfoResp sessionInfoResp = SessionInfoResp.fromJson(commonResp.data);
    _history.value = sessionInfoResp.history ?? [];
  }

  Future<void> send(String sessionId, String text) async {
    if (text.isEmpty) {
      return;
    }
    _history.add(RoleContent(role: "user", content: text));
    String result = "";

    SSEClient.subscribeToSSE(
      method: SSERequestType.POST,
      url: '${RequestApi.baseUrl}${RequestApi.chat}',
      header: {
        "Authorization": SpUtil.getString(Constants.authorization) ?? "",
        // "Accept": "text/event-stream",
        "Cache-Control": "no-cache",
        'Content-Type': 'application/json;charset=utf-8',
      },
      body: ChatReq(sessionId: sessionId, content: text, history: _history)
          .toJson(),
    ).listen(
      (event) {
        String data = event.data!.trim();
        if (data == '[start]') {
          _history.add(RoleContent(role: "assistant", content: ""));
        } else if (data != '[done]') {
          result += data;
          _history.removeLast();
          _history.add(RoleContent(role: "assistant", content: result));
        }
      },
    );
  }
}
