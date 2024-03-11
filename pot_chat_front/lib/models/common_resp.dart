class CommonResp {
  int? code;
  String? message;
  dynamic data;

  CommonResp({this.code, this.message, this.data});

  Map<String, dynamic> toJson() {
    Map<String, dynamic> data = {};
    data['code'] = code;
    data['message'] = message;
    data['data'] = this.data;
    return data;
  }

  CommonResp.fromJson(Map<String, dynamic> json) {
    code = json['code'];
    message = json['message'];
    data = json['data'];
  }

  @override
  String toString() {
    return 'CommonResp(code: $code, message: $message, data: $data)';
  }
}
