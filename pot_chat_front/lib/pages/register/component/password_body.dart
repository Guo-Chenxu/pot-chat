import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:pot_chat_front/pages/register/register_controller.dart';

// 密码框
class PasswordBody extends GetView<RegisterController> {
  const PasswordBody({super.key});

  @override
  Widget build(BuildContext context) {
    return GetX<RegisterController>(
      init: controller,
      initState: (_) {},
      builder: (_) {
        return SizedBox(
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
