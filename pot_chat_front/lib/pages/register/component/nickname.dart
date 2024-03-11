import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:pot_chat_front/pages/register/register_controller.dart';
import 'package:pot_chat_front/routes/app_pages.dart';

// 昵称框
class NickName extends GetView<RegisterController> {
  const NickName({super.key});

  @override
  Widget build(BuildContext context) {
    return SizedBox(
      width: MediaQuery.of(context).size.width / 3,
      child: TextField(
        autofocus: true,
        onChanged: (value) => controller.onUsernameChanged(value),
        decoration: const InputDecoration(
          labelText: '昵称',
          hintText: '',
          prefixIcon: Icon(Icons.person),
        ),
      ),
    );
  }
}
