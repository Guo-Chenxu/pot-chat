import 'package:get/get.dart';
import 'package:logger/logger.dart';
import 'package:pot_chat_front/pages/login/login_controller.dart';
import 'package:pot_chat_front/service/user_service.dart';

class LoginBinding implements Bindings {
  static final Logger _logger = Logger();

  @override
  void dependencies() {
    _logger.i('依赖注入');
    Get.lazyPut<UserService>(() => UserService());
    Get.lazyPut<LoginController>(() => LoginController());
  }
}
