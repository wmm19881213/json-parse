＃ json-parse
## 简单使用和介绍
旨在将JSON结构化数据转换为按照JSON 标签转换成二维表格式；
具体如下：
  在实际开发过程中经常遇见需要将JSON数据转换成二维表的情形，在模型人员根据JSON设计二维表时，一般会将某一标签下的数据转换成二维表如下json：
  ```JSON
  {"method":"winLending.finance.assets.invoiceCheck","requestId":"6ea5f84e-e0d7-477e-9c26-f38044a6a3c2","response":{"totalTax":"46.61","amountTaxCn":"叁佰叁拾柒圆玖角柒分","salesBank":"宁波银行股份有限公司庄市支行52040122000146139","salesPhone":"","invoiceNumber":"48405771","invoiceType":"增值税电子普通发票","purchaserAddressPhone":"","state":"0","purchaserBank":"","machineCode":"539909644510","cardInfo":{"engineNo":"","vehicleNo":"","originPlace":"","passengersLimited":"","idCardNo":"","brandModel":"","tonnage":"","importCertificateNo":"","certificateNo":"","inspectionListNo":"","vehicleType":"","paymentVoucherNo":""},"salesTaxNo":"91330205309006662A","invoiceCode":"033021800111","purchaserName":"个人","salesAddress":"","checkCode":"06226273659961227639","billingDate":"2019-03-21","totalAmount":"291.36","salesAddressPhone":"浙江省宁波市高新科技技术开发区创苑路98号宁波智慧园二期7号楼401,0574-83888082","salesName":"宁波拜尔生活电器有限公司","purchaserTaxNo":"","invoiceTypeCode":"026","items":[{"unitPrice":"386.2100000","taxRate":"0.16","unit":"","amount":"386.21","specificationModel":"","quantity":"1.0000000","tax":"61.79","commodityName":"*家用美容保健电器*德国牙医推荐 拜尔X1S Plus 智能电动牙刷 成人声波充电式震动牙"},{"unitPrice":"","taxRate":"0.16","unit":"","amount":"-94.85","specificationModel":"","quantity":"","tax":"-15.18","commodityName":"*家用美容保健电器*德国牙医推荐 拜尔X1S Plus 智能电动牙刷 成人声波充电式震动牙"}],"remarks":"订单号:90345214758","amountTax":"337.97"}}
  ```
  根据二维模型将此JSON拆分成两个表：
  response  和 response.items
  使用本项目测试类：
  JSONParseMain
  将获取如下结果使用：
 
 ```` json
 {"response":[{"originPlace":"","idCardNo":"","brandModel":"","PARR_ARR_ID":null,"ARR_ID":null,"paymentVoucherNo":"","totalTax":"46.61","amountTaxCn":"叁佰叁拾柒圆玖角柒分","salesBank":"宁波银行股份有限公司庄市支行52040122000146139","salesPhone":"","invoiceNumber":"48405771","invoiceType":"增值税电子普通发票","tonnage":"","purchaserAddressPhone":"","state":"0","vehicleType":"","engineNo":"","purchaserBank":"","machineCode":"539909644510","passengersLimited":"","importCertificateNo":"","salesTaxNo":"91330205309006662A","certificateNo":"","invoiceCode":"033021800111","purchaserName":"个人","salesAddress":"","checkCode":"06226273659961227639","vehicleNo":"","billingDate":"2019-03-21","totalAmount":"291.36","salesAddressPhone":"浙江省宁波市高新科技技术开发区创苑路98号宁波智慧园二期7号楼401,0574-83888082","salesName":"宁波拜尔生活电器有限公司","purchaserTaxNo":"","invoiceTypeCode":"026","inspectionListNo":"","items":null,"remarks":"订单号:90345214758","amountTax":"337.97"}],"response.items":[{"unitPrice":"386.2100000","taxRate":"0.16","unit":"","amount":"386.21","specificationModel":"","quantity":"1.0000000","PARR_ARR_ID":null,"tax":"61.79","ARR_ID":"0","commodityName":"*家用美容保健电器*德国牙医推荐 拜尔X1S Plus 智能电动牙刷 成人声波充电式震动牙"},{"unitPrice":"","taxRate":"0.16","unit":"","amount":"-94.85","specificationModel":"","quantity":"","PARR_ARR_ID":null,"tax":"-15.18","ARR_ID":"1","commodityName":"*家用美容保健电器*德国牙医推荐 拜尔X1S Plus 智能电动牙刷 成人声波充电式震动牙"}]}
 ````
 
  上述json是将原json转换为Map<String,ListMap<String,Object>>,再转换为JSON。
  开发人员可以根据上述已经转换的对象和表中的所需要的对应json值组合成需要二维表。
  
  ## 展开说明
  原理：首先遍历json，当json标签等于对应二维表时将此标签下的值转换成Map<String,Object>,其中Object可能为String，List<Map<String,Object>>,null,如此递归遍历，生成最后的Map<String, List<Map<String,Object>>>;最后调用转换方法生成二维表；
  
  ## 扩展
  1、根标签使用项目提供的默认标签：JSONParseConstants.JSON_ROOT
  2、转换JSON key值：override convertJSONKey方法
  3、修改转换二维表的方式：override covertResults方法，对于特殊的json 标签可以使用此方法单独处理
  
  
  ## 注意
  1、表对应的标签采用“.”分隔，不要带跟标签。
  2、对于较大的json数据且层数较多，一定要增加表名对应的标签，否则可能会导致内存溢出。
  
  ## 最后
  本项目为呱呱学习小组项目，将持续更新中。
  
  
  
  
\

