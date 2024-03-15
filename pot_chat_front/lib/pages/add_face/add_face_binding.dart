import 'package:get/get.dart';
import 'package:pot_chat_front/pages/add_face/add_face_controller.dart';
import 'package:pot_chat_front/service/user_service.dart';

class AddFacebinding implements Bindings {
  @override
  void dependencies() {
    Get.lazyPut<UserService>(() => UserService());
    Get.lazyPut<AddFaceController>(() => AddFaceController());
  }
}
