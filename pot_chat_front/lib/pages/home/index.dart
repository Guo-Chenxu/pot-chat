import 'package:flutter/material.dart';

import 'package:get/get.dart';
import 'package:pot_chat_front/routes/app_pages.dart';

class HomeView extends StatelessWidget {
  const HomeView({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        centerTitle: true,
        title: const Text("首页"),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.start,
          crossAxisAlignment: CrossAxisAlignment.center,
          children: [
            SizedBox(
              //设置容器大小
              width: 320.0,
              height: 203.0,
              child: Image.asset('imgs/logo.png'),
            ),
            Row(
              mainAxisAlignment: MainAxisAlignment.center,
              crossAxisAlignment: CrossAxisAlignment.center,
              children: [
                ElevatedButton(
                  style: ElevatedButton.styleFrom(
                      foregroundColor: Colors.black,
                      backgroundColor: Colors.blue,
                      minimumSize: Size(MediaQuery.of(context).size.width / 4,
                          MediaQuery.of(context).size.width / 20)),
                  onPressed: () => Get.toNamed(AppRoutes.login),
                  child: const Text(
                    '登录',
                    selectionColor: Colors.black,
                    style: TextStyle(color: Colors.white),
                  ),
                ),
                const SizedBox(
                  width: 30,
                ),
                ElevatedButton(
                  style: ElevatedButton.styleFrom(
                      foregroundColor: Colors.black,
                      backgroundColor: Colors.white,
                      minimumSize: Size(MediaQuery.of(context).size.width / 4,
                          MediaQuery.of(context).size.width / 20)),
                  onPressed: () => Get.toNamed(AppRoutes.register),
                  child: const Text('注册'),
                ),
              ],
            ),
          ],
        ),
      ),
    );
  }
}
