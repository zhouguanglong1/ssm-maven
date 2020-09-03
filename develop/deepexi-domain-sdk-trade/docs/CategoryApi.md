# CategoryApi

All URIs are relative to *http://petstore.swagger.wordnik.com/api*

Method | HTTP request | Description
------------- | ------------- | -------------
[**listCategory**](CategoryApi.md#listCategory) | **GET** /conductor/api/v1/categories | 分页查询分类
[**listCategoryAndInterface**](CategoryApi.md#listCategoryAndInterface) | **GET** /conductor/api/v1/categories/interfaces | 分页查询分类(带接口信息)


<a name="listCategory"></a>
# **listCategory**
> Object listCategory()

分页查询分类

分页查询分类

### Example
```java
// Import classes:
//import com.deepexi.sdk.ApiException;
//import com.deepexi.sdk.trade.api.CategoryApi;


CategoryApi apiInstance = new CategoryApi();
try {
    Object result = apiInstance.listCategory();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling CategoryApi#listCategory");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

**Object**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

<a name="listCategoryAndInterface"></a>
# **listCategoryAndInterface**
> Object listCategoryAndInterface()

分页查询分类(带接口信息)

分页请求分类(带接口信息)

### Example
```java
// Import classes:
//import com.deepexi.sdk.ApiException;
//import com.deepexi.sdk.trade.api.CategoryApi;


CategoryApi apiInstance = new CategoryApi();
try {
    Object result = apiInstance.listCategoryAndInterface();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling CategoryApi#listCategoryAndInterface");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

**Object**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

