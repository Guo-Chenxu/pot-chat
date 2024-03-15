import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:logger/logger.dart';
import 'package:pot_chat_front/constants/constants.dart';
import 'package:pot_chat_front/models/user.dart';
import 'package:pot_chat_front/pages/session/session_controller.dart';
import 'package:pot_chat_front/routes/app_pages.dart';
import 'package:sp_util/sp_util.dart';

class DrawerBody extends GetView<SessionController> {
  DrawerBody({super.key});

  User u = User.fromJson(Map.from(SpUtil.getObject(Constants.user)!));

  @override
  Widget build(BuildContext context) {
    return Drawer(
      child: ListView(
        padding: const EdgeInsets.all(0),
        children: <Widget>[
          UserAccountsDrawerHeader(
            accountName: Text(u.nickname!),
            accountEmail: Text(u.email!),
            currentAccountPicture: CircleAvatar(
              backgroundImage: NetworkImage(u.avatar!),
            ),
            decoration: const BoxDecoration(
              image: DecorationImage(
                  fit: BoxFit.cover,
                  image: NetworkImage(
                      'https://ts3.cn.mm.bing.net/th?id=OIP-C.c2nwnaQWnS60LeSj_81zIwHaKj&w=209&h=298&c=8&rs=1&qlt=90&o=6&dpr=2&pid=3.1&rm=2')),
            ),
          ),
          ListTile(
            title: const Text('添加人脸'),
            trailing: const Icon(Icons.add),
            onTap: () => {Get.toNamed(AppRoutes.addFace)},
          ),
          const Divider(),
          ListTile(
            title: const Text('退出登录'),
            trailing: const Icon(Icons.exit_to_app),
            onTap: () => {controller.logout()},
          )
        ],
      ),
    );
  }
}
