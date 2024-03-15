import 'package:get/get.dart';
import 'package:image_picker/image_picker.dart';
import 'package:logger/logger.dart';
import 'package:pot_chat_front/constants/constants.dart';
import 'package:pot_chat_front/http/http_code.dart';
import 'package:pot_chat_front/models/common_resp.dart';
import 'package:pot_chat_front/routes/app_pages.dart';
import 'package:pot_chat_front/service/user_service.dart';
import 'package:sp_util/sp_util.dart';

class AddFaceController extends GetxController {
  final Logger _logger = Logger();
  final ImagePicker _picker = Get.find<ImagePicker>();
  final UserService _userService = Get.find<UserService>();

  String _verifyCode = "";

  void addFace() async {
    final cameraImages = await _picker.pickImage(source: ImageSource.camera);
    if (cameraImages == null) {
      Get.snackbar("拍摄人脸不能为空", "");
      return;
    }
    final image = await cameraImages.readAsBytes();

    CommonResp? commonResp = await _userService.addFace(_verifyCode, image);
    if (commonResp == null || commonResp.code != HttpCode.success) {
      Get.snackbar('出现错误', commonResp?.message ?? '添加人脸失败, 请稍后重试');
      return;
    }
    _logger.i("addFace success");
    Get.snackbar("添加成功", "");
    Get.toNamed(AppRoutes.session);
  }

  void onVerifyCodeChanged(String verifyCode) {
    _verifyCode = verifyCode.trim();
  }

  Future<bool> sendVerifyCode(String email) async {
    _logger.i("addFace send verify code: ${email}");
    if (email.isEmpty || !email.isEmail) {
      Get.snackbar('邮箱不正确', '请输入正确的邮箱');
      return false;
    }

    CommonResp? commonResp = await _userService.sendVerifyCode(email);
    if (commonResp == null || commonResp.code != HttpCode.success) {
      Get.snackbar('出现错误', commonResp?.message ?? '发送验证码失败');
      return false;
    }
    return true;
  }
}
