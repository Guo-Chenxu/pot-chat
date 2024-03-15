import 'package:get/get.dart';
import 'package:image_picker/image_picker.dart';
import 'package:logger/logger.dart';
import 'package:pot_chat_front/constants/constants.dart';
import 'package:pot_chat_front/http/http_code.dart';
import 'package:pot_chat_front/models/common_resp.dart';
import 'package:pot_chat_front/routes/app_pages.dart';
import 'package:pot_chat_front/service/user_service.dart';
import 'package:sp_util/sp_util.dart';

class SessionController extends GetxController {
  final Logger _logger = Logger();
  final ImagePicker _picker = Get.find<ImagePicker>();
  final UserService _userService = Get.find<UserService>();

  void logout() async {
    await _userService.logout();
    SpUtil.clear();
    Get.offAllNamed(AppRoutes.init);
  }

  void addFace() async {
    final cameraImages = await _picker.pickImage(source: ImageSource.camera);
    if (cameraImages == null) {
      Get.snackbar("拍摄人脸不能为空", "");
      return;
    }
    final image = await cameraImages.readAsBytes();
  }
}
