# restful_api_demo
实体类：Carrier(送货人) Shipper(发货人)Order(订单)
要求功能：
创建发货人账号：路径：http://localhost:80/shipper。
请求方式为post。参数：name, password, and phone。No为shipper唯一主键，由系统分配。
![img_3.png](/report/img_3.png)
创建订单：
路径：http://localhost:80/order。
请求方式为post。参数如图：orderno为order唯一主键，由系统分配。其中status状态和location货物位置在创建订单时由系统生成，默认未取货，地点为发货地点。
![img_4.png](/report/img_4.png)
查看送货人历史记录：
路径：http://localhost:80/order/{carrierno}。
请求方式为get。无参数。检索no为{carrierno}的送货员的历史送货记录。
![img_5.png](/report/img_5.png)
追踪运输路径：
路径：http://localhost:80/order/tracking/{orderno}。
请求方式为get。无参数。检索no为{orderno}的订单目前运输地点记录。
![img_6.png](/report/img_6.png)
