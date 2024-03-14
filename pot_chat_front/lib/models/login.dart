import 'package:dio/dio.dart';

class LoginReq {
  String? email;
  String? password;
  MultipartFile? image;

  LoginReq({this.email, this.password, this.image});

  Map<String, dynamic> toJson() {
    Map<String, dynamic> data = {
      "email": email,
      "password": password,
      "image": image,
    };
    return data;
  }

  LoginReq.fromJson(Map<String, dynamic> json) {
    email = json['email'];
    password = json['password'];
    image = json['image'];
  }
}

class LoginResp {
  String? token;
  String? expireTime;
  String? email;
  String? nickname;
  String? avatar;

  LoginResp(
      {this.token, this.expireTime, this.email, this.nickname, this.avatar});

  LoginResp.fromJson(Map<String, dynamic> json) {
    token = json['token'];
    expireTime = json['expireTime'];
    email = json['email'];
    nickname = json['nickname'];
    avatar = json['avatar'];
  }

  Map<String, dynamic> toJson() {
    Map<String, dynamic> data = {
      "token": token,
      "expireTime": expireTime,
      "email": email,
      "nickname": nickname,
      "avatar": avatar,
    };
    return data;
  }
}
