# Md5_SHA1加密算法

Java 自带的加密类MessageDigest类（加密MD5和SHA）：
 * MD5算法是不可逆的，SHA长度要比MD5多出8个字符（32比特）。
 * 普遍使用的三种加密方式：
   * 1、使用位运算符，将加密后的数据转换成16进制
   * 2、使用格式化方式，将加密后的数据转换成16进制（推荐）
   * 3、使用算法，将加密后的数据转换成16进制
   
MD5与SHA-1区别：
 * 由于MD5与SHA-1均是从MD4发展而来，它们的结构和强度等特性有很多相似之处。
 * SHA-1与MD5的最大区别在于其摘要比MD5摘要长32比特（1byte=8bit，相当于长4byte，转换16进制后比MD5多8个字符）。
 * 对于强行攻击：MD5是2128数量级的操作，SHA-1是2160数量级的操作。
 * 对于相同摘要的两个报文的难度：MD5是264数量级的操作，SHA-1是280数量级的操作。
 * 因而，SHA-1对强行攻击的强度更大。
 * 但由于SHA-1的循环步骤比MD5多（80:64）且要处理的缓存大（160比特 : 128比特），SHA-1的运行速度比MD5慢。
 
 
 ![screenshot](https://github.com/ykmeory/Md5_SHA1/blob/master/screenshot.jpg "截图")
