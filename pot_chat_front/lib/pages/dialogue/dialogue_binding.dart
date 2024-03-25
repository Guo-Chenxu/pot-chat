import 'package:get/get.dart';
import 'package:logger/logger.dart';
import 'package:pot_chat_front/pages/dialogue/dialogue_controller.dart';
import 'package:pot_chat_front/service/chat_service.dart';

class DialogueBinding implements Bindings {
  static final Logger _logger = Logger();

  @override
  void dependencies() {
    _logger.i('依赖注入');
    Get.lazyPut<ChatService>(() => ChatService());
    Get.lazyPut<DialogueController>(() => DialogueController());
  }
}
