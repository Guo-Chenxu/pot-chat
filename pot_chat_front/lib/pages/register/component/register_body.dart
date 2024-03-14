import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:pot_chat_front/pages/register/component/avatar_body.dart';
import 'package:pot_chat_front/pages/register/component/email_body.dart';
import 'package:pot_chat_front/pages/register/component/nickname_body.dart';
import 'package:pot_chat_front/pages/register/component/password_body.dart';
import 'package:pot_chat_front/pages/register/component/verify_code_body.dart';
import 'package:pot_chat_front/pages/register/register_controller.dart';
import 'package:pot_chat_front/routes/app_pages.dart';

class RegisterBody extends GetView<RegisterController> {
  const RegisterBody({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        centerTitle: true,
        title: const Text("注册页面"),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          crossAxisAlignment: CrossAxisAlignment.center,
          children: [
            // 头像
            AvatarBody(),
            const SizedBox(height: 20),
            // 昵称
            const NicknameBody(),
            const SizedBox(height: 20),
            // 邮箱
            const EmailBody(),
            const SizedBox(height: 20),
            // 密码
            const PasswordBody(),
            const SizedBox(height: 20),
            // 验证码
            VerifyCodeBody(),
            const SizedBox(height: 20),
            // 按钮
            Row(
              mainAxisAlignment: MainAxisAlignment.center,
              crossAxisAlignment: CrossAxisAlignment.center,
              children: [
                ElevatedButton(
                  child: const Text('注册'),
                  onPressed: () => {controller.register()},
                ),
                const SizedBox(
                  width: 10,
                ),
                ElevatedButton(
                  style: ElevatedButton.styleFrom(backgroundColor: Colors.blue),
                  child: const Text(
                    '已有账号, 去登录',
                    style: TextStyle(color: Colors.white)
                  ),
                  onPressed: () => Get.toNamed(AppRoutes.login),
                )
              ],
            )
          ],
        ),
      ),
    );
  }
}
