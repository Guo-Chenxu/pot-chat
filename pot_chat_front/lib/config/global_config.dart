import 'package:get/get.dart';
import 'package:image_picker/image_picker.dart';
import 'package:logger/logger.dart';
import 'package:pot_chat_front/service/chat_service.dart';
import 'package:pot_chat_front/service/user_service.dart';
import 'package:sp_util/sp_util.dart';

class GlobalConfig {
  static final Logger _logger = Logger();

  static Future init() async {
    _logger.i('开始全局初始化');
    await SpUtil.getInstance();
    Get.put<ImagePicker>(ImagePicker());
    Get.put<UserService>(UserService());
    Get.put<ChatService>(ChatService());
    _logger.i('全局初始化结束');
  }
}
