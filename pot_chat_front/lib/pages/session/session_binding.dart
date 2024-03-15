import 'package:get/get.dart';
import 'package:pot_chat_front/pages/register/register_controller.dart';
import 'package:pot_chat_front/pages/session/session_controller.dart';
import 'package:pot_chat_front/service/chat_service.dart';
import 'package:pot_chat_front/service/user_service.dart';

class SessionBinding implements Bindings {
  @override
  void dependencies() {
    Get.lazyPut<ChatService>(() => ChatService());
    Get.lazyPut<SessionController>(() => SessionController());
  }
}
