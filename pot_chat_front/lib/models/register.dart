class RegisterReq {
  String? email;
  String? nickname;
  String? password;
  String? avatar;
  String? verifyCode;
  
  RegisterReq(
      {this.email, this.nickname, this.password, this.avatar, this.verifyCode});

  RegisterReq.fromJson(Map<String, dynamic> json) {
    email = json['email'];
    nickname = json['nickname'];
    password = json['password'];
    avatar = json['avatar'];
    verifyCode = json['verifyCode'];
  }
  Map<String, dynamic> toJson() {
    Map<String, dynamic> data = {
      "email": email,
      "nickname": nickname,
      "password": password,
      "avatar": avatar,
      "verifyCode": verifyCode,
    };
    return data;
  }
}
