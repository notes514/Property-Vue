/**
 * 验证输入号码是否为11位有效手机号码
 *
 * @param phone 手机号码
 * @returns {boolean} 返回true表示正确，反之亦然
 */
export function isPoneAvailable(phone) {
  return phone.match(/^[1][3,4,5,6,7,8,9][0-9]{9}$/);
}

/**
 * 验证输入的身份证号码是否为18位有效位
 * 详细可参考掘金：https://juejin.cn/post/6993252081322688548
 *
 * @param { String } code 身份证号码
 * @returns {boolean} 返回true表示正确，反之亦然
 */
export function identityIDCard(code) {
  // 身份证号前两位代表区域
  const city = {
    11: '北京',
    12: '天津',
    13: '河北',
    14: '山西',
    15: '内蒙古',
    21: '辽宁',
    22: '吉林',
    23: '黑龙江 ',
    31: '上海',
    32: '江苏',
    33: '浙江',
    34: '安徽',
    35: '福建',
    36: '江西',
    37: '山东',
    41: '河南',
    42: '湖北 ',
    43: '湖南',
    44: '广东',
    45: '广西',
    46: '海南',
    50: '重庆',
    51: '四川',
    52: '贵州',
    53: '云南',
    54: '西藏 ',
    61: '陕西',
    62: '甘肃',
    63: '青海',
    64: '宁夏',
    65: '新疆',
    71: '台湾',
    81: '香港',
    82: '澳门',
    91: '国外 ',
  };
  // 身份证格式正则表达式
  const idCardReg = /^[1-9]\d{5}(19|20)?\d{2}(0[1-9]|1[012])(0[1-9]|[12]\d|3[01])\d{3}(\d|X)$/i;
  // 身份证验证是否通过（true通过、false未通过）
  let isPass = true;

  // 如果身份证不满足格式正则表达式
  if (!code) {
    isPass = false;
  } else if (!code.match(idCardReg)) {
    isPass = false;
  } else if (!city[code.substr(0, 2)]) {
    // 区域数组中不包含需验证的身份证前两位
    isPass = false;
  } else if (code.length === 18) {
    // 18位身份证需要验证最后一位校验位
    code = code.split('');
    // ∑(ai×Wi)(mod 11)
    // 加权因子
    const factor = [7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2];
    // 校验位
    const parity = [1, 0, 'X', 9, 8, 7, 6, 5, 4, 3, 2];
    let sum = 0;
    let ai = 0;
    let wi = 0;
    for (let i = 0; i < 17; i++) {
      ai = parseInt(code[i]);
      wi = factor[i];
      sum += ai * wi; // 开始计算并相加
    }
    const last = parity[sum % 11]; // 求余
    if (last.toString() !== code[17]) {
      isPass = false;
    }
  }
  return isPass
}
