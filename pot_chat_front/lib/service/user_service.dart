import 'dart:io';

import 'package:get/get.dart';
import 'package:pot_chat_front/http/request_apis.dart';
import 'package:pot_chat_front/models/common_resp.dart';

class UserService {
  static UserService get to => Get.find();

  Future<CommonResp?> register(String username, String password) async {
    // Map<String, dynamic> resp = await HttpRequest.formPost(RequestApi.register,
    //     params: RegisterReq(username: username, password: password).toJson());
    // return CommonResp.fromJson(resp);
  }

  Future<CommonResp?> login(String username, String password) async {
    // Map<String, dynamic> resp = await HttpRequest.formPost(RequestApi.login,
    //     params: LoginReq(username: username, password: password).toJson());
    // return CommonResp.fromJson(resp);
  }
}
