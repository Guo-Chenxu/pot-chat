import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:pot_chat_front/pages/login/component/email_body.dart';
import 'package:pot_chat_front/pages/login/component/password_body.dart';
import 'package:pot_chat_front/pages/login/login_controller.dart';
import 'package:pot_chat_front/routes/app_pages.dart';

class LoginBody extends GetView<LoginController> {
  const LoginBody({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        centerTitle: true,
        title: const Text("登录页面"),
      ),
      body: Center(
        child: ListView(
          children: [
            // 用户名
            const EmailBody(),
            const SizedBox(height: 20),
            // 密码
            const PasswordBody(),
            const SizedBox(height: 20),
            // 按钮
            Column(
              mainAxisAlignment: MainAxisAlignment.center,
              crossAxisAlignment: CrossAxisAlignment.center,
              children: [
                Row(
                  mainAxisAlignment: MainAxisAlignment.center,
                  crossAxisAlignment: CrossAxisAlignment.center,
                  children: [
                    ElevatedButton(
                      child: const Text('邮箱密码登录'),
                      onPressed: () => {controller.login()},
                    ),
                    const SizedBox(width: 20),
                    ElevatedButton(
                      child: const Text('人脸登录'),
                      onPressed: () => {controller.faceLogin()},
                    ),
                  ],
                ),
                const SizedBox(height: 20),
                ElevatedButton(
                  style: ElevatedButton.styleFrom(backgroundColor: Colors.blue),
                  child: const Text(
                    '没有账号, 去注册',
                    style: TextStyle(color: Colors.white),
                  ),
                  onPressed: () => Get.toNamed(AppRoutes.register),
                )
              ],
            )
          ],
        ),
      ),
    );
  }
}
