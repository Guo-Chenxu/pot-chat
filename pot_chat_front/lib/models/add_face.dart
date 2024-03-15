import 'package:dio/dio.dart';

class AddFaceReq {
  String? verifyCode;
  MultipartFile? image;

  AddFaceReq({this.verifyCode, this.image});

  Map<String, dynamic> toJson() {
    Map<String, dynamic> data = {
      "verifyCode": verifyCode,
      "image": image,
    };
    return data;
  }

  AddFaceReq.fromJson(Map<String, dynamic> json) {
    verifyCode = json['verifyCode'];
    image = json['image'];
  }
}
