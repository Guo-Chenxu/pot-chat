import 'package:get/get.dart';
import 'package:image_picker/image_picker.dart';
import 'package:logger/logger.dart';
import 'package:pot_chat_front/constants/constants.dart';
import 'package:pot_chat_front/http/http_code.dart';
import 'package:pot_chat_front/models/common_resp.dart';
import 'package:pot_chat_front/models/login.dart';
import 'package:pot_chat_front/models/user.dart';
import 'package:pot_chat_front/routes/app_pages.dart';
import 'package:pot_chat_front/service/user_service.dart';
import 'package:sp_util/sp_util.dart';

class LoginController extends GetxController {
  final UserService _userService = Get.find<UserService>();
  final ImagePicker _picker = Get.find<ImagePicker>();

  static final Logger _logger = Logger();

  final _isObscure = true.obs;

  set isObscure(val) => _isObscure.value = val;
  get isObscure => _isObscure.value;

  changeObscure() => _isObscure.value = !_isObscure.value;

  String _email = "";
  String _password = "";

  onEmailChanged(String username) {
    _email = username.trim();
  }

  onPasswordChanged(String password) {
    _password = password.trim();
  }

  void login() async {
    if (_email.isEmpty || !_email.isEmail) {
      Get.snackbar('邮箱不正确', '请输入正确的邮箱');
      return;
    }
    if (_password.isEmpty) {
      Get.snackbar('密码不能为空', '请输入密码');
      return;
    }

    CommonResp? commonResp = await _userService.login(_email, _password);
    return afterLoginReq(commonResp);
  }

  void faceLogin() async {
    if (_email.isEmpty || !_email.isEmail) {
      Get.snackbar('邮箱不正确', '请输入正确的邮箱');
      return;
    }

    final cameraImages = await _picker.pickImage(source: ImageSource.camera);
    if (cameraImages == null) {
      Get.snackbar("拍摄人脸不能为空", "");
      return;
    }
    final image = await cameraImages.readAsBytes();

    CommonResp? commonResp = await _userService.faceLogin(_email, image);
    return afterLoginReq(commonResp);
  }

  void afterLoginReq(CommonResp? commonResp) async {
    if (commonResp == null || commonResp.code != HttpCode.success) {
      Get.snackbar('出现错误', commonResp?.message ?? Constants.defaultError);
      return;
    }

    LoginResp? loginResp = LoginResp.fromJson(commonResp.data);
    _logger.i("登录成功");
    SpUtil.putString(Constants.authorization, loginResp.token!);
    SpUtil.putObject(
        Constants.user,
        User(
          email: loginResp.email!,
          nickname: loginResp.nickname!,
          avatar: loginResp.avatar!,
        ));
    Get.snackbar('登录成功', '欢迎回来');
    _logger.i("登录 token: ${SpUtil.getString(Constants.authorization)}");
    _logger.i("登录 用户信息: ${SpUtil.getObject(Constants.user)}");
    Get.toNamed(AppRoutes.session);
  }
}
