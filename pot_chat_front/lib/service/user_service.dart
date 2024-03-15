import 'package:get/get.dart';
import 'package:dio/dio.dart' as dio;
import 'package:logger/logger.dart';
import 'package:pot_chat_front/http/http_requests.dart';
import 'package:pot_chat_front/http/request_apis.dart';
import 'package:pot_chat_front/models/common_resp.dart';
import 'package:pot_chat_front/models/register.dart';

class UserService {
  static UserService get to => Get.find();

  static final Logger _logger = Logger();

  Future<CommonResp?> sendVerifyCode(String email) async {
    Map<String, dynamic> resp = await HttpRequest.formPost(
        RequestApi.sendVerifyCode,
        params: {"email": email});
    return CommonResp.fromJson(resp);
  }

  Future<CommonResp?> uploadAvatar(List<int> file) async {
    Map<String, dynamic> resp =
        await HttpRequest.formPost(RequestApi.uploadAvatar, params: {
      "file": dio.MultipartFile.fromBytes(file, filename: 'avatar.jpg')
    });
    return CommonResp.fromJson(resp);
  }

  Future<CommonResp?> register(
      {required String email,
      required String nickname,
      required String password,
      required String avatar,
      required String verifyCode}) async {
    Map<String, dynamic> resp = await HttpRequest.bodyPost(
      RequestApi.register,
      params: RegisterReq(
              email: email,
              nickname: nickname,
              password: password,
              avatar: avatar,
              verifyCode: verifyCode)
          .toJson(),
    );
    return CommonResp.fromJson(resp);
  }

  // 邮箱密码登录
  Future<CommonResp?> login(String email, String password) async {
    Map<String, dynamic> resp = await HttpRequest.formPost(
      RequestApi.login,
      params: {
        "email": email,
        "password": password,
      },
    );
    return CommonResp.fromJson(resp);
  }

  // 人脸登录
  Future<CommonResp?> faceLogin(String email, List<int> image) async {
    Map<String, dynamic> resp = await HttpRequest.formPost(
      RequestApi.login,
      params: {
        "email": email,
        "image": dio.MultipartFile.fromBytes(image, filename: "face.jpg"),
      },
    );
    return CommonResp.fromJson(resp);
  }

  // 退出登录
  Future<CommonResp?> logout() async {
    Map<String, dynamic> resp = await HttpRequest.bodyPost(RequestApi.logout);
    return CommonResp.fromJson(resp);
  }

  // 添加人脸
  Future<CommonResp?> addFace(String verifyCode, List<int> image) async {
    Map<String, dynamic> resp = await HttpRequest.formPost(
      RequestApi.addFace,
      params: {
        "verifyCode": verifyCode,
        "image": dio.MultipartFile.fromBytes(image, filename: "face.jpg"),
      },
    );
    return CommonResp.fromJson(resp);
  }
}
