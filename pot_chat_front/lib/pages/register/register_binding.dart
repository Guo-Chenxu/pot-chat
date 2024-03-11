import 'package:get/get.dart';
import 'package:pot_chat_front/pages/register/register_controller.dart';
import 'package:pot_chat_front/service/user_service.dart';

class RegisterBinding implements Bindings {
  @override
  void dependencies() {
    Get.lazyPut(() => UserService());
    Get.lazyPut<RegisterController>(() => RegisterController());
  }
}
