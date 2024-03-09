import 'package:get/get.dart';
import 'package:logger/logger.dart';
import 'package:sp_util/sp_util.dart';

class GlobalConfig {
  static final Logger _logger = Logger();

  static Future init() async {
    _logger.i('开始全局初始化');
    await SpUtil.getInstance();
    _logger.i('全局初始化结束');
  }
}
