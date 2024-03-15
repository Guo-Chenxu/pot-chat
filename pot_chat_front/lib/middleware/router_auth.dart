import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:logger/logger.dart';
import 'package:pot_chat_front/constants/constants.dart';
import 'package:pot_chat_front/routes/app_pages.dart';
import 'package:sp_util/sp_util.dart';

class RouteAuthMiddleware extends GetMiddleware {
  @override
  int? priority = 0;

  RouteAuthMiddleware({required this.priority});

  final Logger _logger = Logger();

  static final List<String> _skipRoutes = [
    AppRoutes.init,
  ];

  @override
  RouteSettings? redirect(String? route) {
    _logger.i('路径鉴权 Auth route: $route');
    String? token = SpUtil.getString(Constants.authorization) ?? '';

    if (!_skipRoutes.contains(route) && token.isEmpty) {
      Future.delayed(
          const Duration(seconds: 1), () => Get.snackbar('未登录', '请先登录再访问该页面'));
      return const RouteSettings(name: AppRoutes.init);
    }

    return super.redirect(route);
  }
}
