import 'package:get/get.dart';
import 'package:pot_chat_front/constants/constants.dart';
import 'package:pot_chat_front/http/http_code.dart';
import 'package:pot_chat_front/models/common_resp.dart';
import 'package:pot_chat_front/routes/app_pages.dart';
import 'package:pot_chat_front/service/user_service.dart';

class RegisterController extends GetxController {
  final UserService _userService = Get.find<UserService>();

  final _isObscure = true.obs;

  set isObscure(val) => _isObscure.value = val;
  get isObscure => _isObscure.value;

  changeObscure() => _isObscure.value = !_isObscure.value;

  String _nickname = "";
  String _password = "";
  String _verifyCode = "";
  String _email = "";
  String _avatar = "";

  void onNicknameChanged(String nickname) {
    _nickname = nickname.trim();
  }

  void onPasswordChanged(String password) {
    _password = password.trim();
  }

  void onVerifyCodeChanged(String verifyCode) {
    _verifyCode = verifyCode.trim();
  }

  void onEmailChanged(String email) {
    _email = email.trim();
  }

  void onAvatarChanged(String avatar) {
    _avatar = avatar.trim();
  }

  Future<bool> sendVerifyCode() async {
    if (_email.isEmpty || !_email.isEmail) {
      Get.snackbar('邮箱不正确', '请输入正确的邮箱');
      return false;
    }

    CommonResp? commonResp = await _userService.sendVerifyCode(_email);
    if (commonResp == null || commonResp.code != HttpCode.success) {
      Get.snackbar('出现错误', commonResp?.message ?? '发送验证码失败');
      return false;
    }
    return true;
  }

  void register() async {
    if (_avatar.isEmpty) {
      Get.snackbar('头像不能为空', '请选择头像');
      return;
    }
    if (_nickname.isEmpty) {
      Get.snackbar('昵称不能为空', '请给自己取一个好听的昵称吧');
      return;
    }
    if (_email.isEmpty || !_email.isEmail) {
      Get.snackbar('邮箱不正确', '请输入正确的邮箱');
      return;
    }
    if (_password.isEmpty) {
      Get.snackbar('密码不能为空', '请输入密码');
      return;
    }
    if (_verifyCode.isEmpty) {
      Get.snackbar('验证码不能为空', '请输入验证码');
      return;
    }

    // CommonResp? commonResp = await _userService.register(_username, _password);
    // if (commonResp == null || commonResp.code != HttpCode.success) {
    //   Get.snackbar('出现错误', commonResp?.message ?? Constants.defaultError);
    //   return;
    // }

    Get.snackbar('注册成功', '快去登录吧');
    const String route = AppRoutes.login;
    Get.toNamed(route);
  }
}
