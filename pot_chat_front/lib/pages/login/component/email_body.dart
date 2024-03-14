import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:pot_chat_front/pages/login/login_controller.dart';

// 邮箱框
class EmailBody extends GetView<LoginController> {
  const EmailBody({super.key});

  @override
  Widget build(BuildContext context) {
    return SizedBox(
      width: MediaQuery.of(context).size.width / 2,
      child: TextField(
        autofocus: true,
        onChanged: (value) => controller.onEmailChanged(value),
        decoration: const InputDecoration(
          labelText: '邮箱',
          hintText: '用于登录的邮箱',
          prefixIcon: Icon(Icons.email),
        ),
      ),
    );
  }
}
