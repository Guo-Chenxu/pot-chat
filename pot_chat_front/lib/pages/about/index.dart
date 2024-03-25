import 'package:flutter/gestures.dart';
import 'package:flutter/material.dart';
import 'package:url_launcher/url_launcher.dart';

class AboutView extends StatelessWidget {
  const AboutView({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        centerTitle: true,
        title: const Text("关于小锅"),
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
            const Center(
              child: Text(
                "hello, 大家好, 我是小锅, 您的私人心理医生",
              ),
            ),
            const SizedBox(height: 20),
            Center(
              child: RichText(
                text: TextSpan(
                  text: '下面是我的',
                  style: const TextStyle(color: Colors.black),
                  children: [
                    TextSpan(
                      text: 'github地址',
                      style: const TextStyle(
                        color: Colors.blue,
                        decoration: TextDecoration.underline,
                      ),
                      recognizer: TapGestureRecognizer()
                        ..onTap = () {
                          _launchURL('https://github.com/Guo-Chenxu/pot-chat');
                        },
                    ),
                  ],
                ),
              ),
            ),
            const SizedBox(height: 5),
            const Center(
              child: Text(
                "如果觉得不错, 还请动动小手麻烦给个star哟 φ(゜▽゜*)♪",
              ),
            ),
            const SizedBox(height: 10),
            Center(
              child: GestureDetector(
                onTap: () async {
                  String url = "https://github.com/Guo-Chenxu/pot-chat";
                  _launchURL(url);
                },
                child: const Text(
                  'https://github.com/Guo-Chenxu/pot-chat',
                  style: TextStyle(
                    color: Colors.blue,
                    decoration: TextDecoration.underline,
                  ),
                ),
              ),
            ),
          ],
        ),
      ),
    );
  }

  void _launchURL(String url) async {
    if (await canLaunch(url)) {
      await launch(url);
    } else {
      throw '无法打开链接：$url';
    }
  }
}
