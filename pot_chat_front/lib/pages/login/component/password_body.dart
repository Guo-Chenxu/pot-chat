import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:pot_chat_front/pages/login/login_controller.dart';

// 密码框
class PasswordBody extends GetView<LoginController> {
  const PasswordBody({super.key});

  @override
  Widget build(BuildContext context) {
    return GetX<LoginController>(
      init: controller,
      initState: (_) {},
      builder: (_) {
        return SizedBox(
          width: MediaQuery.of(context).size.width / 2,
          child: TextField(
            autocorrect: false,
            obscureText: _.isObscure,
            autofocus: true,
            onChanged: (value) => controller.onPasswordChanged(value),
            decoration: InputDecoration(
              labelText: '密码',
              hintText: '请输入密码',
              prefixIcon: const Icon(Icons.password),
              suffixIcon: IconButton(
                onPressed: () {
                  _.changeObscure();
                },
                icon: Obx(() {
                  return Icon(
                      _.isObscure ? Icons.visibility_off : Icons.visibility);
                }),
              ),
            ),
          ),
        );
      },
    );
  }
}
