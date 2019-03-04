package com.jeesite.modules.sys.service;	
	
import com.jeesite.common.config.Global;	
import com.jeesite.common.io.FileUtils;	
import com.jeesite.common.io.ResourceUtils;	
import com.jeesite.common.shiro.cas.CasOutHandler;	
import com.jeesite.common.validator.ValidatorUtils;	
import com.jeesite.common.web.http.ServletUtils;	
import java.io.File;	
import java.io.InputStream;	
import java.io.UnsupportedEncodingException;	
import java.security.GeneralSecurityException;	
import java.security.KeyFactory;	
import java.security.MessageDigest;	
import java.security.PrivateKey;	
import java.security.PublicKey;	
import java.security.interfaces.RSAPrivateKey;	
import java.security.interfaces.RSAPublicKey;	
import java.security.spec.PKCS8EncodedKeySpec;	
import java.security.spec.X509EncodedKeySpec;	
import java.text.SimpleDateFormat;	
import java.util.Arrays;	
import java.util.Collection;	
import java.util.Collections;	
import java.util.Comparator;	
import java.util.Date;	
import java.util.HashMap;	
import java.util.HashSet;	
import java.util.Iterator;	
import java.util.List;	
import java.util.Map;	
import javax.crypto.Cipher;	
import javax.crypto.SecretKey;	
import javax.crypto.spec.IvParameterSpec;	
import javax.crypto.spec.SecretKeySpec;	
import javax.servlet.http.HttpServletRequest;	
import org.apache.commons.io.IOUtils;	
import org.hyperic.sigar.CpuInfo;	
import org.hyperic.sigar.NetInterfaceConfig;	
import org.hyperic.sigar.Sigar;	
	
public final class j {	
   private static Date B = new Date();	
   private static final String G = Global.getProperty("productName");	
   private static final l$li d = new l$li();	
   private static final String[] c;	
   private static final String ALLATORIxDEMO = Global.getProperty("companyName");	
	
   public static final Map ALLATORIxDEMO(InputStream inputStream) {	
      byte[] var10000 = new byte[0];	
      boolean var10002 = true;	
	
      try {	
         byte[] a = IOUtils.toByteArray(inputStream);	
         String[] a = CasOutHandler.ALLATORIxDEMO ("PzPz奉爟伎夹｢肹呈甝乄耐粕旽彡ｈ呦优?\u0015ｴsVu_vXr[ｈ曹厳ｴ\b\u0007'\u000b*\r![v^xRxR$Y\u000f\u0003k%\u001e\u0005pX7>0Y![<\u0003*X\u001e'\u0005Sy\u000e\t'\r- ?\r,\u0005*\u0005 \u0006\t/\u001f,\u0005-)}\u0019t,\u0005?\u0001(\u0005/\u0017-\u0005\u0002|\u0019#\t\u000e\f\u0005\t\u0001/\u0005\u0001\u0003,\u0005#\t\u0002q\u0000\u000b,\u0012+o?\u000e\u001d\n-o'0\u0007w\u001c<\t\t\f\u0010;| ,\u001e0;\u0016\u0004<\u001d(\"\u0002-<\u0003}Z1(\u0011\u0000\u000f;\u0015_/W\u000f;#\b\u0013&vE\u0016\u0002\tV\u0000/>^\f=\u0000$\u001cWpE\u0014\t7\t5\u0019w'\u000bY6\\1=\r6\u0015\r5>\u00008\u000b\u0016\u0003:k\u001f\u0011\u000f\u0017# \u001d',\u001d\u001b\u0007Zv\r\u000b\n-,q(p\r\n)\"A-> ?)X\u000e4\t,\u0006:.-\u0003<0\"!\u0002k\u001d\u0007\f|\u0002 \u00070\u001f+\u0014r#,$\"<\u0005\t\t,\u0005/\u0001-#7\u0006A)6\u0017$r\b\u000f\u000fpZ\u000f_\u0005\u0005\u0002$3\u001f#\u001e\u0014->+\n##9!9#<\u0005Y=\u0004\u000b\b,-p+\u0000; \u000f\u0016\u0000\u0010\u0016\u000f%'\u0014 \u000b};\u0005*\u0013*6\f-\n\u0014&\u0012''&\u0014=\u0010\\\u0017&\u0017,\u0007\u0007  #\u000b\u0002A+\u0016o#\u0016Yv\u0019\u0015<\u00169(Y,\u0004)/\u0011&\u0016)*\u001a|'\u0007\u0007\u0007;492\u0014}7\u0014:(8\u000b<\u0005$3\u0005v\u0019/,2]>,2\u000b\u0011\n\u0016\\+\"\u0002\u000b\n?s/\u0016\u0018\u001c\u0002\u0000\"\u00159u\u0017&/\u0001?\u000e,\u0005>!$=-t'k\\\u000e)o,(\u001e\u0011\u0014\u0011\t.%(!(?4\u0002qY\u000f\b\u000f\n\r(u\u001d\u000eW#/1>\u000f\u0005p\u001f\f\f\u0012\b0\r4X\u0014\u001f\u0015\u0006\u0013+\u0006\u001a>\u001c\u0007\u001d& (\u0001\")sY>\u0007\u0017%0Z\u0015<\";|-\u0015?\u0000$t\u000f\u001e%v\u0006\u000b^\u0011\u001f\u0015*\u0014\u0018-/5;\u001c[\u001e-w=p-r-\u0011:\u0006!->=\"3E)\"(4\u0006\t#E.\u0018=#1#3\u0000<%k\u0003}\b\u000f[,\b\u001e\t#?\r[\u0011\u000bw\u0006\u0012'&\u00057\u001a $\u0000\b\u0005\u0005\u0005\u0000(\u0003,\u0003\u0001\\ #\u001c^|^\u000b=>\u001b =7\u001e0'\u0007>&\u0007%^p8\u0002Ww\u0007\t\u0018&7\u0017[u'%!#[2)7\u001b>!v\u000b#+0\f\u0016Z'8'[v/(V\u001eZ\u000e\u0003o9<$\u0013\r*\u001e*-\u001e\u0018\u0005\u0005\u0005W\u0016\u0014w\u00062\u0003\nY\u0014\u0006,^6W7!\u001c\n ;\u0017>)\u001ds#6=\u001d#4_\f\u0006>\u0001\u001e\u0016>\u0014 A|_\"\u0018\"\u001d\u0010\u001f\u00076\u001e\f+ *^\u0003Y1!\u001c^\u000392\f\u0011\u001f\u000f(/W\t##\t-\u001c.4#\u001c\u0005\u0005\u0006Z.[\u000b-tY+\u0018w\u000f\u0016=}A,\u0019\f\t\u0017\u0004|\u0007\u000f/\u0010\u00067/\u0000!=\t\u0000<-\b\u001c\u000b\b:\f/\u0005\u00030&\u0006& =|\u0002\u0007\u001f\r\u0018>\u0000\u0005#\u0005#\f<%[\n\\2X,\\-[}\"\u0001+v7\b!}\u0002$><6,\u0019\u0017\u0017\u001d%\b&\u0015\u0002)\r<[}((X\u0015Sy\u000e\t'\u0003\b\t/t)\u0007=5)\u0017'&]\u0000?\u0001,\u0005?\u0011/\u0005Z\u0003 \u0005*\u0007,-?\u000f,#?\u0007\u000b\u001471/k,t \u0014V\u0016)w\u001f|\u0014\u001c\u0004)\u0005\u001d\u001c\u0005\u0014'\nq'\t%&!/*&\u00003,*[ \u001e3\u0000u-4^5,\u0017\u001e\u0002%'\u0007\u0011\u001a26w\u0018rE\f=+?\u0013W\u0011+\u0012\u00142\u0006\u000b\u0000t\u0003#\u001d\u0010)\u0005\u001c3\nq\n\"8%<u_\u0005\f\u0014?\u0001\b<$.\u0000\rXpW\b\u001c.#)\u0019p +E\u0005!o\u0002p\",>\u0013\u001b|]\t\u0005p\u000b\u0016Z\u00026\t97Z2',\\(\u0003\u0013\"\u0010#\u0007\u0017p\u0003\u0003%*\n7:>\u000f)\u0001v?\r*\u0005?\u0005,").split("`");	
         String[] a = "f3872c937e1652955cbee3619b864e91,7c38063084e3765a827c2312c4f4e2f,784dec4e53fecd6da47684178958d72,34274c64b0b20a2a29739a99496248,44e2e93b7b4d8b6339609592b88a1906,46862bd120c8bdacdd709291c5387d1,fd08746352f716baacd46e919ab778c,1a7036e08c569d026e2d7d042b1b4eb,34b10939f23bde7d45286b7fd9d4ed9,c20a2b20d13ed8b26e25d8fc47bbc3,2e1bc7e75d42ec57d7694d8e02bbd24,3bd556a5a38b1f07a274e950ce7c9fc2,e7b96e008884f1c6e57e1c75f6578136,93046b26ef875d897450dccb97ca1f,6e11f9bd5833fa0214bd6115061e9aa,0d8935c3af2aa2274fa2e13541466c05,530a75f762123a9675a841239e9e61,6410e44f0e79b2c2d052644bc5c397d8,6579ad12404a4e4d94a4de2ad87e4485,7464ae3e8c1614e5de36760ac29259,3c163acb8749503e9747650aadc876c,0eed2ae3d997bb03cfe149d19c4845d5,101606de6ab16a83d4196635a33169c5,2e0608555913296cbfdf5a5f4eda9855,e4c3603043a07e166869f6008cb7c926,ea8f190975228b615e4aa8a71a08dd65,a5d13220e30cdb4e96c265ebb32c8ada,829c34d79cd6760b51e4b00e1197de,c4cbeb9cdd1d9f675414959b62c9b32,c9d0508f132de9db8a854fc511be7d0b,6d286b0834c67d94c809debd5a4192d9,a8b72a5afe72feed739dbb8839390af,99ba5b0815ce659db60dac3a317c0e,6d5d5b0cdc27c61a3b829e963c739ef,0b78a085931c256e70c21dfa88f16882,7baa67a12c3abe5e0b8ea964ba26656f,1a0bd4129cd3aa99d49561789700ae8,edb5031797a3ae6a8d3384fb0b02ee,c956a0e7bcc87c1d08a1478261f7ade8,3293189594bd8ac90a5ac125f8799ab,b2637d676b91403dcba6c14cf9739745,d65a2847c786878cde410e56e9b7e2d,9705cb21825a11540d7c691874a698,1e9aa02b60b19334c61928b569f36db,927ea61993a68a1d8637e7fd1b743c58,2c2c917f18844510d6db6d0783aa0cb,2e01686dc904ac3d8499ef567ea1a5,2e0e19500995007072c286956a7c0832,d1f2ffa4702e051fd82c526abca7ad,3873c5614ab476a7b1ebcc634a41822,0b57437996ba989dd23015fa855205a,6c7b1e63590fb942dc181daeac0a4a9a,dd93f99734cb619bc329e1b5adc043ab,66a3179cce9737ce0e183deed031d8a4,e8d90395b648935a84282e8c22ffe9c,96cd1fa72db8fcf59ec062f210ee196,5c190cfd1aa64a72be683bc0a801d59a,466ace7053a07b8c51a91da40cd55f,8c3911092477502020258751e10c7,990659ac44c4a97ea276d48da6e843d0,b59711b57e1f9c73cce060154fd44a,b749321770d10a3185b4e16cac3d649e,a887492dc8664555b9370b0ac508ee28,50ce95409e7fe58a7c95a168fcf02,e93a836abccb4cf7a3289b62163a70,4e9b6e594805de41b055cd5871c3fad,a835d2ad8d709b5e0c2574730c55434,c8e693954deba3cdc9a4b690599e238,fbb4fb8567010e47cc21d42361f4405b,7e0dd95c80d0b0433662c98edd8d6db9,c33262b14714a79c41b4b3822d2d5dc,8c334c7f3727b0705118435b13523d41,9ce0434e018b52fedb0ec063bd531352,19df6a06d25750a2de57eea57476bb59,650623a562e4d4636d8ac8b9c589268c,48decf243dbb452253e0ebbf3c99d35,11d3669d14c7ba7d95a39c85c0aac39,9e42db6b5c76b6ba2350e066991a8b6f,655ac624500649e61d834ccab740c618,086879615120ea4ec4a150070d65b6,9c93ad110471d0124606efa5ef966b,bbd12e058c673d049602574cd4e1c0,32cb0116077bf6a71af6dc81856474c0,89ce60bbdd0e5ed8bd872b5434176b99,30584c1cbe5ef40e994b94e7cde7792,b32fb3bd5b3ce01d98bb4a1fb3efb096,e4f0bb6ade5ae60bc0ed3cb95b7b3b5,eed0f9dfa9a771285a9f16b2bdec4e,ede748ae38a1d5b17726c5aa09c8ccd,1235a6817ca2ed4db4a76fa8e21c76c7,57fde00de77b36444ca131d3ce4056fd,8de783ce896510ca22eda25db76bd92f,b77d871051331f5dadb2d3af0f8f3fd,367a7d6f3dca39a059987a6bc3453c6,6d83830e6ddbb951c8bc51de378e79,b6aad665cbca9a59535c160655674e8,6882d5deb16b4ca94ce690e6448af5c1,null,2678a70eac3d40d84dd317b6a63ba3a,9ddc552533b617091902837065520547,9758c96a387bb6abcbab2d80c6ea999,04c2aa475c71569eb08add6d6014df,635140c8a94ef382be7df98e51c0946,6fcfce3489bebea33fd065191478e2,ed54e3b4c8e6399ba611e716b545e92,b65f64e39991f936770d310734f4867,d066a8f0353b8bd250674389fdd3ad,cf983b5aadee29d22c15dfb686838300,e997b17c80d5cf5262d385866c8775,1dd23801eb2ad3648732761740e0e96f,9af4a484c1a41b3a999ef212a3124c11,43b1d6a9e60d20ce770f1b911fefb8c9,55109a5ea42ebe5e1de6a80898c8bc9,375e6489c389e1b763e56c4d9d705f80,ea353839618004ac9813f2e59212213,1251dceb238f53e9ac6f2e7a98cbafe9,80e1d8a8bd6a0d4f7fd1e3301c93d02,b0143899e17f7dd6a097ea47d6205749,2e99e3b18ecd0c04151e3dded65a9c,d969ed1a8130d1520387c0dad5becbe,4683cba6eab57e21ed6c282de353bb,4ca9bb95d51c2572e82e4473a1c3996f,24037453acddfb05816b34f0464012e8,9213488371b0a3653c1f3005c3691a8,aa78ee11ed3d70a173e706cf9a752da,20240f43a0529271a06e192a7b98d93,61ae0c8ed54a1eedbc46ac08e8d08a5,05313bc6063dacdb1c54e297e9a4863,d476adcbc59bd9af3b4f7e8907a9dc,c981cfe39130ce965eb9bd29b58f4a4c,8fd37574675947314234c743a7e7015,98c877bc868a8ad4b0295d1d9ab435d,7a004876cea7982cf7465e494eebee7,2caa78df870e04899ec7ee562b8e540,9828feb6ac433ccf1a32239a9cce9aa,6b8a368129412f11b32139ad56dbeacb,03f1e70c5cc4bab0e2752b1659d00c8,cb7b16b3b761a0d9eb7b16235b805de,43893996227d59c0307891acd95a540a,2a5c1354b89c854b0dbd761ee83bb,2e5beb9f05d7ed65a7065d2458348b3,c64dd506580586635d661a0a91ef39cc,e7149243057534beed68a2fc8c060946,88bbbb3a18344aa9653a5c1f077c8110,0bc9c7ed8a5c861587cb99f715c2377,b0028f0d5107dd839730c8a4bb49ce,c7f38686009299adb6fed8b8d165ac46,a632df82c5751e34658bd4795b6649c4,d8818ba26673dc38054995de5571fa25,a3c05f318cb93d94607d55ac587f004,c5a580b6cddba8801d8892d16348825,eabf2161b8dca5dcba56e43cb8093526,15f6b2de550a15de1966b7b0b3d3c5a2,0b905fbc8a4978e9988262ccc5a7aa82,79d76d58e14e8e79466aa11413642b,971ed3ef7b0b92c3a8db8fe6c7ee918,2cd0a070c029c19171d044469bbad98,77dc0c19a07649e8cb9f9e9a9e2f1d,33eb48792577ec555899732391e433cd,2a9f00a4565b0718c5c5483ec90e58a,b371b6bd7908d55d9dc090c5ec1aa5,cf19052b4a3d2d57bfa772bd5725bf1,4dd3ddc011d31063da8e4c47a076c96a,47554a0a7a8a2e9d210c075226acb,b9d9d832f4fe23750b13118bb096fc,bf02c05c214e8441bb786d30785e380f,b7c5dc3c2280f9b401cac8dea79664c1,3d1b091f20791a3b17c58f92bcd7650c,30f0b54cf11e8c7b5a0e4bf6d4d3a7f2,b2aba8ad863a4df252a22917fb4ea05,c8e48ae8ed34d332b57deb86a25abd6,c046d22031225835212cfc91385c5,958dc94488a5f463c97b83ea238417f8,43ec0a07196d0f8cad29712762ac4b1,5d9c4b13da77d221e704e88e422ce4d3,dec2945bedcc168f557048610adf80,74ad69d3b02c3951861aeed47362e72,a0063f6deb7a1664ed6ac74476b6412,f9e990383b31ff5484784282bb1a4,01b7c87f43a8461db75b22de1a6dd6,6064a8f83db958c688a85295d23586b,456e9b1c7e7cda73e31673135525782,cdd748c1fbc4e3948db1e4bd861db3,4f6add1ec4b5bc473b33e65e206308,c0fb8c7c68d5698b163e5bba108470b1,18b73bdfcd474765809b1e2530d1867,3a3716022c30a20c9e73c2be2c793a3,bd9b0f123864703219f34d19c65d933,20e27033aac4599fcf8482a4a61f07b,9794e1423d2087a199711ebb0619c5e,89e419e1edbefb80f282ad722d7ddc,1c7b34b62cd2b5e728f5b6238dadb86,0ca851e3d2d693a2e7586c52e91b58d,9e8402066b683530bb511233919fb2,935b47eb965b9ab9e2963d51c23e8b5,e77b0adbcd6ee832c7e9dab35662d909,682d0686ccce2c120cdc45a0f922ac23,076be3a8c6b0e35f2020185bdc41102b,3b8ae4e26d71cfdfb626c74f8ce43c5,b6700062a040de25c131a7b2b29e67e,f89649a71f38510e8b33ee366184d4b,a39fac22c9e61d145e1d481fbd0551,094ea8216537543bdd554855a6e6f167,d2e45f8e7f731b257422cf2ee6d91d06,2619d235f85b941d15c8e6d1cba72b38,7a00bd548307ba85d87f99ec727425,ba55a4d26f540d1fa6e3e8426655b2,86ba8115280b29e32ab405761bae8,7822cdae87c40315a906216642872b,917bb243661d9906586678d3c23529,e7a291cd8c768d3cb55280aa80eb9b3,18a6eba9eda916d996e7eb69deb1bb2b,11f003fea68136a34ef9b365d4d36dd,b3ab095783aefd040a1ba9c2dd2fce,0c850dfe8d49f7e6cab153411461389,74bacabbdf221544096ac8cb430d54b1,37419152765f5702730c7d7acd01fd,59a78a4671a28a1b046edbc13b2d6142,a55b45851ee89ba67e2a4db4e2d79fb,a690a759bbecdcb2b387bec48e31d62,a8c659973752b3a2dc4d84d3acce5dae,5749e23a06644a4387e7274d3837d120,0a08131b3fa69c426a3fc4adedaf4,82301ab9075b233a52bb8e86665f442c,21b1d0c8d241d6325471292bf00ac6e6,7616c22d41a62828a6600eb7163076,f39c3be336df06cea3732cfe78ac72,74b89f62bf298b908b50b168acc15cd,a04ca33cad59d63242f5e4e8f382e6d5,076de0ab22428449e94883bc40b8eee,14ca5a1e01b2d2c9c7a8f762c5f19799,9da79fba65c6c535bbbf305d2cdec701,30b731008a91a3ab71b7c80a1c9a179,d7c1172fb007cbce81619abd4e87851,55f549b20d5ab4c3c572227b81da5d3b,50dd020e06d436f8b6049d55a0c6992,2996e101928ab65dbf591d1736c3b93,3a54705312601c0fb425811aef427b80,e088418926ecb5632399c2f5c9a00acb,41a938be07b8153a4ba2f063564a3b,a230827c122919a7ab85228e25ab1,d200cc9db64dde92a56938b3de2b3,5339676192502cb1c6e57b3874beee57,4fb55195b0855a0ca45e0d3a49453c58,b43c4d0ed551335a0139e03533cc4e7,4bc983497f59b5962ce669d29b8d0ae3,435cade3dbf1239dac7466a77d4ecbc,3c1c1d5d869278480a6d48c171c7d77,997cf0c385ec402cfb5eb18a60c2d6,c68fa17a67c6d0e5a2d00eb6549ee,b801d6b0b140d04379a993382df8c2,35713a6ce9bf8c4c1d8e50132096384d,5d5c107dd1200a1d8a83d36d145190c,20872bb877eb54d67967d1c39ac07117,dad0be2a7075800ce6e6dd33ac2266e,9dd1c76e7f94e88ed5e07ed6565f3bae,16344466b48577ed129ca09e03bdb145,4d3586c521994e771c07648bbf76b8ef,2b9ae605674381e229ad43244340592,46085296b01662f4b812bda5035727,01412588a1113631f487cc015e1e6069,5fa833a7d4b53d8aaf41bfa15652e709,99473e7a5b909a46b504a5e1cbfafa,a01d9d26b29d91a33ac9c16ce2dc0884,47f14c6cfcca591a1284605558a3c180,b287086298b26f3c162640a3133c2696,41e2ca5dc14574cde58550c21c1fe68".split(",");	
         byte[] a = H(a[3]);	
         byte[] a = H(a[1]);	
         String a = H(ALLATORIxDEMO(new String(ALLATORIxDEMO(a, a, a), "UTF-8"), "\n\n", "\n\n"), "\n", "");	
	
         try {	
            a = ALLATORIxDEMO(H(a, a[4]), a[2]);	
         } catch (Exception var20) {	
            if (ALLATORIxDEMO(c, a)) {	
               a = ALLATORIxDEMO(a, a[2]);	
            }	
         }	
	
         int a = 96;	
         int a = 96 + 32;	
         byte[] a = ALLATORIxDEMO(a.substring(a, a));	
         int a = a;	
         a += 32;	
         byte[] a = ALLATORIxDEMO(a.substring(a, a));	
         a = a + 160;	
         a = a.length() - 64 - a[0].length();	
         byte[] a = ALLATORIxDEMO(a.substring(a, a));	
         String a = new String(ALLATORIxDEMO(a, a, a), "UTF-8");	
         HashMap a = new HashMap();	
         String[] var15;	
         int var16 = (var15 = a.substring(2, a.length() - 2).split("","")).length;	
	
         int var17;	
         for(int var24 = var17 = 0; var24 < var16; var24 = var17) {	
            String[] a = var15[var17].split("":"");	
            if ("updateCode".equals(a[0])) {	
               a = a.length == 2 ? a[1] : "";	
            } else {	
               a.put(a[0], a.length == 2 ? a[1] : "");	
            }	
	
            ++var17;	
         }	
	
         Collection var26 = a.values();	
         String[] var10001 = new String[a.values().size()];	
         boolean var10003 = true;	
         List a = Arrays.asList(var26.toArray(var10001));	
         Collections.sort(a, new Comparator() {	
            public int ALLATORIxDEMO(String ax, String axx) {	
               return ax.compareTo(axx);	
            }	
         });	
         String a = "";	
	
         Iterator var28;	
         for(Iterator var27 = var28 = a.iterator(); var27.hasNext(); var27 = var28) {	
            String a = (String)var28.next();	
            a = (new StringBuilder()).insert(0, a).append(a).toString();	
         }	
	
         return !ALLATORIxDEMO((String)a, 88).equals(a) ? new HashMap() : a;	
      } catch (Exception var21) {	
         return new HashMap();	
      }	
   }	
	
   // $FF: synthetic method	
   private static final String ALLATORIxDEMO(String input, int iterations) {	
      try {	
         MessageDigest a;	
         byte[] a = (a = MessageDigest.getInstance("MD5")).digest(input.getBytes("UTF-8"));	
	
         int a;	
         for(int var10000 = a = 1; var10000 < iterations; var10000 = a) {	
            ++a;	
            a.reset();	
            a = a.digest(a);	
         }	
	
         return ALLATORIxDEMO(a);	
      } catch (Exception var5) {	
         throw new RuntimeException(var5);	
      }	
   }	
	
   // $FF: synthetic method	
   private static boolean ALLATORIxDEMO(String[] str, String[] strs) {	
      if (str != null && strs != null) {	
         String[] var2 = str;	
         int var3 = str.length;	
	
         int var4;	
         for(int var10000 = var4 = 0; var10000 < var3; var10000 = var4) {	
            String a = var2[var4];	
            String[] var6 = strs;	
            int var7 = strs.length;	
	
            int var8;	
            for(var10000 = var8 = 0; var10000 < var7; var10000 = var8) {	
               String a = var6[var8];	
               if (a.equals(ALLATORIxDEMO(a))) {	
                  return true;	
               }	
	
               ++var8;	
            }	
	
            ++var4;	
         }	
      }	
	
      return false;	
   }	
	
   static {	
      HashSet a = new HashSet();	
	
      try {	
         Sigar a;	
         CpuInfo[] a;	
         String a = (a = (a = com.jeesite.common.l.l.I.B).getCpuInfoList()).length > 0 ? a[0].getModel() : "";	
         String[] a = a.getNetInterfaceList();	
	
         for(int a = 0; a < a.length; ++a) {	
            NetInterfaceConfig a;	
            if (((a = a.getNetInterfaceConfig(a[a])).getFlags() & 8L) == 0L && !"127.0.0.1".equals(a.getAddress()) && !"0.0.0.0".equals(a.getAddress()) && !"00:00:00:00:00:00".equals(a.getHwaddr())) {	
               a.add(ALLATORIxDEMO((String)(new StringBuilder()).insert(0, a).append(a.getDescription()).append(a.getHwaddr()).toString(), 76));	
            }	
         }	
      } catch (Throwable var7) {	
      }	
	
      String[] var10001 = new String[a.size()];	
      boolean var10003 = true;	
      c = (String[])a.toArray(var10001);	
   }	
	
   // $FF: synthetic method	
   private static String H(String input) {	
      try {	
         return new String(null.ALLATORIxDEMO(input), "UTF-8");	
      } catch (UnsupportedEncodingException var2) {	
         return "";	
      }	
   }	
	
   // $FF: synthetic method	
   private static String H(String contentBase64, String publicKeyBase64) throws Exception {	
      PublicKey a = ALLATORIxDEMO(publicKeyBase64);	
      Cipher a;	
      (a = Cipher.getInstance("RSA")).init(2, a);	
      int a = ((RSAPublicKey)a).getModulus().bitLength() / 8;	
      byte[][] a = ALLATORIxDEMO(ALLATORIxDEMO(H(contentBase64)), a);	
      StringBuffer a = new StringBuffer();	
      byte[][] var8 = a;	
      int var9 = a.length;	
	
      int var10;	
      for(int var10000 = var10 = 0; var10000 < var9; var10000 = var10) {	
         byte[] a = var8[var10];	
         ++var10;	
         a.append(new String(a.doFinal(a)));	
      }	
	
      return H(a.toString());	
   }	
	
   public static final l$li ALLATORIxDEMO() {	
      long a = System.currentTimeMillis() - B.getTime();	
      if (d.isLoaded() && a / 86400000L <= 0L) {	
         return d;	
      } else {	
         B = new Date();	
         HashMap a = new HashMap();	
	
         try {	
            InputStream a = null;	
            String a = (new StringBuilder()).insert(0, ResourceUtils.getResource("config/jeesite.yml").getFile().getParentFile().getParentFile().getParent()).append(File.separator).append("jeesite.lic").toString();	
            File a;	
            if ((a = new File(a)).exists()) {	
               a = FileUtils.openInputStream(a);	
            }	
	
            if (a == null) {	
               throw new Exception("您当前的版本为社区版");	
            }	
	
            a.putAll(ALLATORIxDEMO((InputStream)a));	
         } catch (Exception var6) {	
            a.put("message", var6.getMessage());	
         }	
	
         a.putAll(ALLATORIxDEMO((Map)a));	
         a.put("modules", ",flow,weixin,");	
         a.put("openModules", (new StringBuilder()).insert(0, ",").append((String)a.get("openModules")).append(",").toString());	
         String var10000 = (String)a.get("type");	
         String[] var10001 = new String[2];	
         boolean var10003 = true;	
         var10001[0] = "1";	
         var10001[1] = "2";	
         if (ALLATORIxDEMO(var10000, var10001)) {	
            a.put("nJob", "true");	
            a.put("fnCas", "true");	
         }	
	
         var10000 = (String)a.get("type");	
         var10001 = new String[1];	
         var10003 = true;	
         var10001[0] = "2";	
         if (ALLATORIxDEMO(var10000, var10001)) {	
            a.put("fnCluster", "true");	
            a.put("fnSaas", "true");	
            a.put("fnI18n", "true");	
            a.put("nMsg", "true");	
         }	
	
         d.putAll(a);	
         StringBuilder a = new StringBuilder();	
         a.append("\r\n    " + (String)a.get("message") + "\r\n");	
         a.append((new StringBuilder()).insert(0, "\r\n    机器码是：").append(c.length > 0 ? c[0] : "").append("").toString());	
         a.append((new StringBuilder()).insert(0, "\r\n    产品名称：").append(G).append("").toString());	
         a.append((new StringBuilder()).insert(0, "\r\n    公司名称：").append(ALLATORIxDEMO).append("").toString());	
         a.append("\r\n");	
         d.put("loadMessage", a.toString());	
         com.jeesite.common.l.l.I.ALLATORIxDEMO();	
         return d;	
      }	
   }	
	
   // $FF: synthetic method	
   private static String ALLATORIxDEMO(String str) {	
      return str == null ? null : str.replaceAll("^[\s|　| ]*|[\s|　| ]*$", "");	
   }	
	
   // $FF: synthetic method	
   private static String H(String text, String searchString, String replacement) {	
      return ALLATORIxDEMO(text, searchString, replacement, -1, false);	
   }	
	
   // $FF: synthetic method	
   private static PublicKey ALLATORIxDEMO(String publicKey) throws Exception {	
      byte[] a = H(publicKey);	
      X509EncodedKeySpec a = new X509EncodedKeySpec(a);	
      return KeyFactory.getInstance("RSA").generatePublic(a);	
   }	
	
   // $FF: synthetic method	
   private static byte[] H(String input) {	
      return null.ALLATORIxDEMO(input);	
   }	
	
   public static final Map ALLATORIxDEMO(Map info) {	
      Map a = new HashMap();	
      String a = "0";	
      String a = "社区版";	
	
      try {	
         if (!ALLATORIxDEMO((CharSequence)info.get("message"))) {	
            throw new Exception((String)info.get("message"));	
         }	
	
         int a = false;	
         String[] var5;	
         int var6 = (var5 = c).length;	
	
         int var7;	
         for(int var10000 = var7 = 0; var10000 < var6; var10000 = var7) {	
            if (var5[var7].equals(ALLATORIxDEMO((String)info.get("code")))) {	
               a = true;	
            }	
	
            ++var7;	
         }	
	
         if (!a || ALLATORIxDEMO((CharSequence)G) || ALLATORIxDEMO((CharSequence)ALLATORIxDEMO) || !ALLATORIxDEMO(G).equals(ALLATORIxDEMO((String)info.get("productName"))) || !ALLATORIxDEMO(ALLATORIxDEMO).equals(ALLATORIxDEMO((String)info.get("companyName")))) {	
            throw new Exception((new StringBuilder()).insert(0, "您当前的版本为").append(a).toString());	
         }	
	
         a = (String)info.get("type");	
         a.put("type", a);	
         Map var16;	
         if ("1".equals(a)) {	
            a = "个人版";	
            var16 = info;	
         } else {	
            if ("2".equals(a)) {	
               a = "专业版";	
            }	
	
            var16 = info;	
         }	
	
         String a = (String)var16.get("expireDate");	
         if (!"-1".equals(a)) {	
            long a;	
            Date a;	
            if ((a = ((a = (new SimpleDateFormat("yyyy-MM-dd")).parse(a)).getTime() - System.currentTimeMillis()) / 3600000L / 24L) <= 0L) {	
               throw new Exception((new StringBuilder()).insert(0, "您的").append(a).append("许可，于").append((new SimpleDateFormat("yyyy年MM月dd日")).format(a)).append("已到期").toString());	
            }	
	
            HashMap var17;	
            if (a <= 7L) {	
               var17 = a;	
               a.put("message", (new StringBuilder()).insert(0, "您当前的版本为").append(a).append("，许可即将到期，还剩余最后").append(a).append("天。").toString());	
            } else if (a <= 60L) {	
               a.put("message", (new StringBuilder()).insert(0, "您当前的版本为").append(a).append("，许可即将到期，还剩余").append(a).append("天。").toString());	
               var17 = a;	
            } else {	
               a.put("message", (new StringBuilder()).insert(0, "您当前的版本为").append(a).append("，许可到期时间为：").append((new SimpleDateFormat("yyyy年MM月dd日")).format(a)).append("。").toString());	
               var17 = a;	
            }	
	
            var17.put("title", (new StringBuilder()).insert(0, a).append("（剩余").append(a).append("天）").toString());	
         } else {	
            a.put("message", (new StringBuilder()).insert(0, "您当前的版本为").append(a).append("，非常感谢您对我们产品的认可与支持！").toString());	
            a.put("title", a);	
         }	
      } catch (Exception var13) {	
         a.put("message", (new StringBuilder()).insert(0, var13.getMessage()).append("，官方网站：http://jeesite.com").toString());	
         a.put("title", a);	
         a.put("type", a);	
      }	
	
      return a;	
   }	
	
   // $FF: synthetic method	
   private static byte[][] ALLATORIxDEMO(byte[] bytes, int splitLength) {	
      int a;	
      byte[][] var10000 = new byte[bytes.length % splitLength != 0 ? (a = bytes.length / splitLength + 1) : (a = bytes.length / splitLength)][];	
      boolean var10002 = true;	
      byte[][] a = var10000;	
	
      int a;	
      for(int var7 = a = 0; var7 < a; var7 = a) {	
         byte[] a;	
         byte[] var8;	
         if (a == a - 1 && bytes.length % splitLength != 0) {	
            var8 = new byte[bytes.length % splitLength];	
            var10002 = true;	
            a = var8;	
            System.arraycopy(bytes, a * splitLength, a, 0, bytes.length % splitLength);	
            var10000 = a;	
         } else {	
            var8 = new byte[splitLength];	
            var10002 = true;	
            a = var8;	
            var10000 = a;	
            System.arraycopy(bytes, a * splitLength, a, 0, splitLength);	
         }	
	
         var10000[a++] = a;	
      }	
	
      return a;	
   }	
	
   // $FF: synthetic method	
   private static boolean ALLATORIxDEMO(CharSequence cs, CharSequence... searchCharSequences) {	
      if (!ALLATORIxDEMO(cs) && searchCharSequences != null && searchCharSequences.length > 0) {	
         CharSequence[] var2 = searchCharSequences;	
         int var3 = searchCharSequences.length;	
	
         int var4;	
         for(int var10000 = var4 = 0; var10000 < var3; var10000 = var4) {	
            CharSequence a = var2[var4];	
            if (ALLATORIxDEMO((CharSequence)cs, (CharSequence)ALLATORIxDEMO(a.toString()))) {	
               return true;	
            }	
	
            ++var4;	
         }	
	
         return false;	
      } else {	
         return false;	
      }	
   }	
	
   // $FF: synthetic method	
   private static PrivateKey ALLATORIxDEMO(String privateKey) throws Exception {	
      byte[] a = H(privateKey);	
      PKCS8EncodedKeySpec a = new PKCS8EncodedKeySpec(a);	
      return KeyFactory.getInstance("RSA").generatePrivate(a);	
   }	
	
   // $FF: synthetic method	
   private static boolean ALLATORIxDEMO(String str, String... strs) {	
      if (str != null && strs != null) {	
         String[] var2 = strs;	
         int var3 = strs.length;	
	
         int var4;	
         for(int var10000 = var4 = 0; var10000 < var3; var10000 = var4) {	
            String a = var2[var4];	
            if (str.equals(ALLATORIxDEMO(a))) {	
               return true;	
            }	
	
            ++var4;	
         }	
      }	
	
      return false;	
   }	
	
   // $FF: synthetic method	
   private static String ALLATORIxDEMO(byte[] input) {	
      String a = "";	
      StringBuilder a = new StringBuilder("");	
	
      int a;	
      for(int var10000 = a = 0; var10000 < input.length; var10000 = a) {	
         a = Integer.toHexString(input[a] & 255);	
         a.append(a.length() == 1 ? (new StringBuilder()).insert(0, "0").append(a).toString() : a);	
         ++a;	
      }	
	
      return a.toString().trim();	
   }	
	
   // $FF: synthetic method	
   private static byte[] ALLATORIxDEMO(String input) {	
      int a = false;	
      int a = false;	
      int a;	
      byte[] var10000 = new byte[a = input.length() / 2];	
      boolean var10002 = true;	
      byte[] a = var10000;	
	
      int a;	
      for(int var9 = a = 0; var9 < a; var9 = a) {	
         int a;	
         int a = (a = a * 2 + 1) + 1;	
         int a = Integer.decode((new StringBuilder()).insert(0, "0x").append(input.substring(a * 2, a)).append(input.substring(a, a)).toString());	
         int var10001 = a;	
         byte var10 = Byte.valueOf((byte)a);	
         ++a;	
         a[var10001] = var10;	
      }	
	
      return a;	
   }	
	
   // $FF: synthetic method	
   private static String ALLATORIxDEMO(String contentBase64, String privateKeyBase64) throws Exception {	
      PrivateKey a = ALLATORIxDEMO(privateKeyBase64);	
      Cipher a;	
      (a = Cipher.getInstance("RSA")).init(2, a);	
      int a = ((RSAPrivateKey)a).getModulus().bitLength() / 8;	
      byte[][] a = ALLATORIxDEMO(ALLATORIxDEMO(H(contentBase64)), a);	
      StringBuffer a = new StringBuffer();	
      byte[][] var8 = a;	
      int var9 = a.length;	
	
      int var10;	
      for(int var10000 = var10 = 0; var10000 < var9; var10000 = var10) {	
         byte[] a = var8[var10];	
         ++var10;	
         a.append(new String(a.doFinal(a)));	
      }	
	
      return H(a.toString());	
   }	
	
   // $FF: synthetic method	
   private static boolean ALLATORIxDEMO(CharSequence cs, boolean ignoreCase, int thisStart, CharSequence substring, int start, int length) {	
      if (cs instanceof String && substring instanceof String) {	
         return ((String)cs).regionMatches(ignoreCase, thisStart, (String)substring, start, length);	
      } else {	
         int a = thisStart;	
         int a = start;	
         int a = length;	
         int a = cs.length() - thisStart;	
         int a = substring.length() - start;	
         if (thisStart >= 0 && start >= 0 && length >= 0) {	
            if (a >= length && a >= length) {	
               char a;	
               char a;	
               do {	
                  int var10000 = a;	
	
                  while(true) {	
                     --a;	
                     if (var10000 <= 0) {	
                        return true;	
                     }	
	
                     a = cs.charAt(a);	
                     ++a;	
                     a = substring.charAt(a);	
                     ++a;	
                     if (a != a) {	
                        if (!ignoreCase) {	
                           return false;	
                        }	
                        break;	
                     }	
	
                     var10000 = a;	
                  }	
               } while(Character.toUpperCase(a) == Character.toUpperCase(a) || Character.toLowerCase(a) == Character.toLowerCase(a));	
	
               return false;	
            } else {	
               return false;	
            }	
         } else {	
            return false;	
         }	
      }	
   }	
	
   // $FF: synthetic method	
   private static int ALLATORIxDEMO(CharSequence cs, CharSequence searchChar, int start) {	
      return cs.toString().indexOf(searchChar.toString(), start);	
   }	
	
   // $FF: synthetic method	
   private static boolean ALLATORIxDEMO(CharSequence cs) {	
      int a;	
      if (cs != null && (a = cs.length()) != 0) {	
         int a;	
         for(int var10000 = a = 0; var10000 < a; var10000 = a) {	
            if (!Character.isWhitespace(cs.charAt(a))) {	
               return false;	
            }	
	
            ++a;	
         }	
	
         return true;	
      } else {	
         return true;	
      }	
   }	
	
   // $FF: synthetic method	
   private static boolean H(CharSequence cs1, CharSequence cs2) {	
      if (cs1 == cs2) {	
         return true;	
      } else if (cs1 != null && cs2 != null) {	
         if (cs1.length() != cs2.length()) {	
            return false;	
         } else {	
            return cs1 instanceof String && cs2 instanceof String ? cs1.equals(cs2) : ALLATORIxDEMO(cs1, false, 0, cs2, 0, cs1.length());	
         }	
      } else {	
         return false;	
      }	
   }	
	
   // $FF: synthetic method	
   private static byte[] ALLATORIxDEMO(byte[] input, byte[] key, byte[] iv) {	
      try {	
         SecretKey a = new SecretKeySpec(key, "AES");	
         IvParameterSpec a = new IvParameterSpec(iv);	
         Cipher a = Cipher.getInstance("AES/CBC/PKCS5:adding");	
         a.init(2, a, a);	
         return a.doFinal(input);	
      } catch (GeneralSecurityException var6) {	
         throw new RuntimeException(var6);	
      }	
   }	
	
   // $FF: synthetic method	
   private static String ALLATORIxDEMO(String str, String open, String close) {	
      if (str != null && open != null && close != null) {	
         int a;	
         int a;	
         return (a = str.indexOf(open)) != -1 && (a = str.indexOf(close, a + open.length())) != -1 ? str.substring(a + open.length(), a) : null;	
      } else {	
         return null;	
      }	
   }	
	
   // $FF: synthetic method	
   private static boolean ALLATORIxDEMO(CharSequence seq, CharSequence searchSeq) {	
      if (seq != null && searchSeq != null) {	
         return ALLATORIxDEMO(seq, searchSeq, 0) >= 0;	
      } else {	
         return false;	
      }	
   }	
	
   public static final boolean ALLATORIxDEMO(HttpServletRequest request) {	
      if (request == null) {	
         request = ServletUtils.getRequest();	
      }	
	
      if (request != null) {	
         String a = request.getContextPath();	
         String a = request.getRequestURL().toString();	
         com.jeesite.common.l.l.I.ALLATORIxDEMO(a, a);	
         String[] var10000 = new String[2];	
         boolean var10002 = true;	
         boolean var10005 = false;	
         var10000[0] = a + "/licence";	
         var10000[1] = (new StringBuilder()).insert(0, a).append("/licence/save").toString();	
         String[] a = var10000;	
         if (ALLATORIxDEMO(request.getRequestURI(), a)) {	
            return true;	
         }	
	
         String a = (String)d.get("domainOrIp");	
         if (!H((CharSequence)((CharSequence)d.get("type")), (CharSequence)"0") && !ALLATORIxDEMO((CharSequence)a)) {	
            a = (new StringBuilder()).insert(0, "://127.0.0.1,://localhost,").append(a).toString();	
            if (!ALLATORIxDEMO((CharSequence)a, (CharSequence[])a.split(","))) {	
               return false;	
            }	
         }	
      }	
	
      return true;	
   }	
	
   // $FF: synthetic method	
   private static String ALLATORIxDEMO(String text, String searchString, String replacement, int max, boolean ignoreCase) {	
      if (!ALLATORIxDEMO((CharSequence)text) && !ALLATORIxDEMO((CharSequence)searchString) && replacement != null && max != 0) {	
         String a = text;	
         if (ignoreCase) {	
            a = text.toLowerCase();	
            searchString = searchString.toLowerCase();	
         }	
	
         int a = 0;	
         int a;	
         if ((a = a.indexOf(searchString, a)) == -1) {	
            return text;	
         } else {	
            int a = searchString.length();	
            int a = (a = replacement.length() - a) < 0 ? 0 : a;	
            a *= max < 0 ? 16 : (max > 64 ? 64 : max);	
            StringBuilder a = new StringBuilder(text.length() + a);	
            int var10000 = a;	
	
            StringBuilder var11;	
            while(true) {	
               if (var10000 == -1) {	
                  var11 = a;	
                  break;	
               }	
	
               a.append(text, a, a).append(replacement);	
               --max;	
               a = a + a;	
               if (max == 0) {	
                  var11 = a;	
                  break;	
               }	
	
               var10000 = a = a.indexOf(searchString, a);	
            }	
	
            var11.append(text, a, text.length());	
            return a.toString();	
         }	
      } else {	
         return text;	
      }	
   }	
}	
