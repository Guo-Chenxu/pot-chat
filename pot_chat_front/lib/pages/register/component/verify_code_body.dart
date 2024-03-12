import 'dart:async';

import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:pot_chat_front/pages/register/register_controller.dart';

// 验证码框
class VerifyCodeBody extends GetView<RegisterController> {
  VerifyCodeBody({super.key});

  RxInt countDownTime = 0.obs;
  Timer? timer;
  RxBool enable = true.obs;

  void dispose() {
    timer?.cancel();
  }

  void startCountdownTimer() {
    countDownTime.value = 60;
    enable.value = false;
    timer = Timer.periodic(const Duration(seconds: 1), (timer) {
      if (countDownTime.value == 0) {
        enable.value = true;
        timer.cancel();
        return;
      }
      countDownTime.value--;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Row(
      crossAxisAlignment: CrossAxisAlignment.center,
      mainAxisAlignment: MainAxisAlignment.center,
      children: [
        SizedBox(
          width: MediaQuery.of(context).size.width / 2 * 2 / 3,
          child: TextField(
            autofocus: true,
            onChanged: (value) => controller.onVerifyCodeChanged(value),
            decoration: const InputDecoration(
              labelText: '验证码',
              hintText: '请输入验证码',
              prefixIcon: Icon(Icons.verified),
            ),
          ),
        ),
        Obx(
          () => SizedBox(
            width: MediaQuery.of(context).size.width / 2 / 3,
            child: ElevatedButton(
              style: ElevatedButton.styleFrom(
                backgroundColor: enable.value ? Colors.cyan : Colors.grey,
              ),
              child: Text(
                countDownTime.value == 0 ? '发送' : '${countDownTime.value}s',
                style: const TextStyle(fontSize: 10, color: Colors.white),
              ),
              onPressed: () {
                if (!enable.value) return;
                controller.sendVerifyCode().then((value) => {
                      if (value) {startCountdownTimer()}
                    });
              },
            ),
          ),
        ),
      ],
    );
  }
}
