# SQL & API

## 1 /users

| user_id | user_name | password |
| ------- | --------- | -------- |
| int     | String    | String   |

### 1.1 /users/login 登录

**请求URL：**

```
/users/login
```

**请求方式：**

```
Post
```

**参数说明：**

|   参数   | 是否必选 |  类型  |  说明  |
| :------: | :------: | :----: | :----: |
| userName |    是    | String | 用户名 |
| password |    是    | String |  密码  |

**返回示例：**

```javascript
{
  "code": "001",
    "user": {
    "user_id": 1,
    "userName": "admin"
  },
  "msg": "登录成功"
}

{'code': '004', 'msg': '用户名或密码错误'}
```



### 1.2 /users/findUserName 查找用户名是否存在

**请求URL：**

```
/users/findUserName
```

**请求方式：**

```
Post
```

**参数说明：**

|   参数   | 是否必选 |  类型  |  说明  |
| :------: | :------: | :----: | :----: |
| userName |    是    | string | 用户名 |

**返回示例：**

```javascript
{
  "code": "001",
  "msg": "用户名不存在，可以注册"
}

{'code': "004", 'msg': '用户名已经存在，不能注册'}
```



### 1.3 /users/register 注册

**请求URL：**

```
/user/register
```

**请求方式：**

```
Post
```

**参数说明：**

|   参数   | 是否必选 |  类型  |  说明  |
| :------: | :------: | :----: | :----: |
| userName |    是    | String | 用户名 |
| password |    是    | String |  密码  |

**返回示例：**

```javascript
{
  code: '001',
  msg: '注册成功'
}

{'code': "004", 'msg': '用户名已经存在，不能注册'}
```



## 2 /product

| product_id | product_category | product_name | product_intro | product_picture | product_price | product_sales |
| ---------- | ---------------- | ------------ | ------------- | --------------- | ------------- | ------------- |
| int        | int              | String       | String        | String          | double        | int           |



### 2.1  /product/getPromoProduct 首页展示同一类别前3个商品
注: 可根据需要自定义数量

**请求URL：**

```
/product/getPromoProduct
```

**请求方式：**

```
Post
```

**参数说明：**

|    参数     | 是否必选 | 类型 |  说明  |
| :---------: | :------: | :--: | :----: |
| category_id |    是    | int  | 类别名 |



**返回示例：**

```javascript
{
	"code": "001",
	"product": [
		{
			"product_id": 1,
            "product_category": 1,
			"product_name": "CSGO",
			"product_intro": "第一人称射击",
            "product_picture":	"/public/gameimg/fate.jpg",
			"product_price": 59.99,
			"product_sales": 56
		},
        ......
	]
}
```

### 2.2  /product/getDetails 单个商品详情

**请求URL：**

```
/product/getDetails
```

**请求方式：**

```
Post
```

**参数说明：**

|    参数    | 是否必选 | 类型 |  说明  |
| :--------: | :------: | :--: | :----: |
| product_id |    是    | int  | 商品ID |

**返回示例：**

```javascript
{
	"code": "001",
	"product": [
		{
			"product_id": 1,
            "product_category": 1,
			"product_name": "CSGO",
			"product_intro": "第一人称射击",
            "product_picture":	"/public/gameimg/fate.jpg",
			"product_price": 59.99,
			"product_sales": 56
		}
	]
}
```

### 2.3  /product/getAllProduct 获取全部商品

**请求URL：**

```
/product/getAllProduct/
```

**请求方式：**

```
Post
```

**参数说明：**

|       参数       | 是否必选 | 类型 |  说明  |
| :--------------: | :------: | :--: | :----: |
| product_category |    非    |  []  | 类别ID |

注: 	product_category 是分类查询的时候才携带  ( 4.6 获取指定类别商品 ), 

		product_category 为空执行的是全部数据查询
**返回示例：**

```javascript
{
	"code": "001",
	"product": [
		{
			"product_id": 1,
            "product_category": 1,
			"product_name": "CSGO",
			"product_intro": "第一人称射击",
            "product_picture":	"/public/gameimg/fate.jpg",
			"product_price": 59.99,
			"product_sales": 56
		},
		
	],
	"total": 70		(数据总数)
}
```



### 2.4  /product/getProductBySearch 搜索商品

**请求URL：**

```
/product/getProductBySearch/
```

**请求方式：**

```
Post
```

**参数说明：**

|    参数     | 是否必选 | 类型 |          说明           |
| :---------: | :------: | :--: | :---------------------: |
|   search    |    是    | str  |        搜索内容         |
|  pageSize   |    是    | int  |        页码大小         |
| currentPage |    是    | int  | 当前页(设置1为首页即可) |

**返回示例：**

1、 搜索条件为某个分类名称 (完全匹配) ,返回该分类的全部商品信息

seach : 充电宝

```javascript
{
	"code": "001",
	"Product": [
		{
			"product_id": 1,
            "product_category": 1,
			"product_name": "CSGO",
			"product_intro": "第一人称射击",
            "product_picture":	"/public/gameimg/fate.jpg",
			"product_price": 59.99,
			"product_sales": 56
		},
		...
	],
	"total": 2
}
```



### 2.5  /product/getDetailsPicture/ 获取指定商品的全部图片

**请求URL：**

```
/product/getDetailsPicture/
```

**请求方式：**

```
Post
```

**参数说明：**

| 参数      | 是否必选 | 类型 | 说明   |
| --------- | -------- | ---- | ------ |
| productID | 是       | int  | 商品ID |

**返回示例：**

```
{
	"code": "001",
	"ProductPicture": [
		{
			"id": 1,
			"product_id": 1,
			"product_picture": "/public/gameimg/fate.jpg",
			"intro": null
		},
	]
}
```



## 3 category

| category_id | category_name |
| ----------- | ------------- |
| int         | String        |

### 3.1  /product/getCategory 获取分类列表

**请求URL：**

```
/product/getCategory/
```

**请求方式：**

```
Post
```

**参数说明：**

无参数

**返回示例：**

```javascript
{
	"code": "001",
	"category": [
		{
			"category_id": 1,
			"category_name": "roleplay"
		},
		{
			"category_id": 2,
			"category_name": "action"
		},
        ...
	]
}
```



## 4 carousel

### 4.1 /carousel/getCarousel

**请求URL：**

```
/carousel/getCarousel
```



**请求方式：**

```
Post
```



(无参数的post请求，可考虑改为get方式)

**参数说明：**

无参数

**返回示例：**

```
{
	"code": "001",
	"carousel": [
		{
			"carousel_id": 1,
			"imgPath": "public/imgs/carousel/carousel1.jpg"
		},
		...
	]
}
```



## 5 user

### 5.1 user/shoppingCart

#### 5.11 user/shoppingCart/getShoppingCart 获取购物车信息

**请求URL：**

```
/user/shoppingCart/getShoppingCart/
```

**请求方式：**

```
Post
```

**参数说明：**

| 参数    | 是否必选 | 类型 | 说明   |
| ------- | -------- | ---- | ------ |
| user_id | 是       | int  | 用户id |

**返回示例：**

```
{
	"code": "001",
	"shoppingCartData": [
		{
			"id": 4,
			"productID": 36,
			"num": 1,
		},
        ......
	]
}
        
        
可能购物车为空，即返回:
{
	"code": "001",
	"shoppingCartData": []
}
```



#### 5.12 user/shoppingCart/addShoppingCart 添加购物车

**请求URL：**

```
/user/shoppingCart/addShoppingCart
```

**请求方式：**

```
Post
```

**参数说明：**

| 参数       | 是否必选 | 类型 | 说明   |
| ---------- | -------- | ---- | ------ |
| user_id    | 是       | int  | 用户id |
| product_id | 是       | int  | 商品id |

**返回示例：**

```
{
	"code": "002",
	"msg": "商品已在购物车,数量+1"
}

{
	"code": "003",
	"msg": "商品已达到购物限额"
}

{
	"code": "001",
	"msg": "添加购物车成功!",
	"shoppingCartData": [
		{
			"id": 1,
			"productID": 1,
			"productName": "CSGO",
			"productImg": "/public/gameimg/fate.jpg",
			"price": 59.99,
			"num": 1,
			"maxNum": 5,
			"check": false
		}
	]
}
```



#### 5.13 user/shoppingCart/deleteShoppingCart 删除购物车

**请求URL：**

```
/user/shoppingCart/deleteShoppingCart
```

**请求方式：**

```
Post
```

**参数说明：**

| 参数       | 是否必选 | 类型 | 说明   |
| ---------- | -------- | ---- | ------ |
| user_id    | 是       | int  | 用户id |
| product_id | 是       | int  | 商品id |

**返回示例：**

```
{
	"code": "001",
	"msg": "删除购物车成功"
}

{
	"code": "002",
	"msg": "该商品不在购物车"
}
```



#### 5.14 /user/shoppingCart/updateShoppingCart/ 更新购物车

**请求URL：**

```
user/shoppingCart/updateShoppingCart/
```

**请求方式：**

```
Post
```

**参数说明：**

| 参数       | 是否必选 | 类型 | 说明   |
| ---------- | -------- | ---- | ------ |
| user_id    | 是       | int  | 用户id |
| product_id | 是       | int  | 商品id |
| num        | 是       | int  | 数量   |

**返回示例：**

```
{"code": "001", "msg": "修改购物车数量成功"}

{'code': '002', 'msg': '该商品不在购物车'}

{'code': '003', 'msg': '数量没有发生变化'}

{"code": "004", "msg": "数量不合法"}

{"code": "004", "msg": "商品已达到购物限额"}
```



### 5.2 /user/order

#### 5.21 /user/order/addOrder 添加订单

**请求URL：**

```
/user/order/addOrder/
```

**请求方式：**

```
Post
```

**参数说明：**

| 参数     | 是否必选 | 类型       | 说明           |
| -------- | -------- | ---------- | -------------- |
| user_id  | 是       | int        | 用户ID         |
| products | 是       | [{…}, {…}] | 结算的全部商品 |

```
products结构  (用到的部分)
{
  productID: "", // 商品id
  price: "", // 商品价格
  num: "", // 商品数量
}
```



**返回示例：**

```
{'code': '001', 'msg': '购买成功'}
{'code': '002', 'msg': '购买失败'}
```



#### 5.22 /user/order/getOrder/ 获取已有订单

**请求URL：**

```
/user/order/getOrder/
```

**请求方式：**

```
Post
```

**参数说明：**

| 参数    | 是否必选 | 类型 | 说明   |
| ------- | -------- | ---- | ------ |
| user_id | 是       | int  | 用户ID |

**返回示例：**

```
{
	"code": "001",
	"orders": [
		[
			{
				"id": 1,
				"order_id": 11659522650709,
				"user_id": 1,
				"product_id": 1,
				"product_num": 1,
				"order_time": 1659522650709,
				"product_price": 59.99,
				"product_name": "CSGO",
				"product_picture": ""
			}
		],
	]
}

{'code': '002', 'msg': '该用户没有订单信息'}
```



### 5.3 /user/collect

#### 5.31 /user/collect/getCollect 查看收藏

**请求URL：**

```
/user/collect/getCollect
```

**请求方式：**

```
Post
```

**参数说明：**

| 参数    | 是否必选 | 类型 | 说明   |
| ------- | -------- | ---- | ------ |
| user_id | 是       | int  | 用户ID |

**返回示例：**

```
{
	"code": "001",
	"collectList": [
		{
			"product_id": 3,
			"category_id": 1,
			"product_name": "",
			"product_title": "",
			"product_intro": "",
			"product_picture": "",
			"product_price": 2799,
			"product_selling_price": 2599,
			"product_num": 20,
			"product_sales": 0
		},
		......
	]
}

        
{	'code': '002',
	 'msg': '该用户没有收藏的商品'
}
```



#### 5.32 /user/collect/getCollect/ 添加收藏

**请求URL：**

```
/user/collect/getCollect/
```

**请求方式：**

```
Post
```

**参数说明：**

| 参数       | 是否必选 | 类型 | 说明   |
| ---------- | -------- | ---- | ------ |
| user_id    | 是       | int  | 用户ID |
| product_id | 是       | int  | 商品ID |

**返回示例：**

```
{
	"code": "001",
	"msg": "添加收藏成功"
}
{
	"code": "003",
	"msg": "该商品已经添加收藏，请到我的收藏查看"
}
```



#### 5.33 /user/collect/deleteCollect/ 删除收藏

(由于前端界面采用卡片式布局，删除选择了逐一删除)

**请求URL：**

```
/user/collect/deleteCollect/
```

**请求方式：**

```
Post
```

**参数说明：**

| 参数       | 是否必选 | 类型 | 说明   |
| ---------- | -------- | ---- | ------ |
| user_id    | 是       | int  | 用户ID |
| product_id | 是       | int  | 商品ID |

**返回示例：**

```
{
	"code": "001",
	"msg": "删除收藏成功"
}

{
	"code": "002",
	"msg": "该商品不在收藏列表"
}
```

