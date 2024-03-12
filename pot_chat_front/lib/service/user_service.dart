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
    Map<String, dynamic> resp = await HttpRequest.bodyPost(RequestApi.register,
        params: RegisterReq(
                email: email,
                nickname: nickname,
                password: password,
                avatar: avatar,
                verifyCode: verifyCode)
            .toJson());
    return CommonResp.fromJson(resp);
  }

  Future<CommonResp?> login(String username, String password) async {
    // Map<String, dynamic> resp = await HttpRequest.formPost(RequestApi.login,
    //     params: LoginReq(username: username, password: password).toJson());
    // return CommonResp.fromJson(resp);
  }
}
