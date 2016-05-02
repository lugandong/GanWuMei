package com.dimon.ganwumei.network;

/**
 *
 * Created by Dimon on 2016/3/31.
 */
//public class HttpMethods {
//    public static final String BASE_URL = "http://gank.io/api/";
//
//    private static final int DEFAULT_TIMEOUT = 5;
//
//    private Realm mRealm;
//    private static RestAPI restAPI;
//    private Retrofit retrofit;
//
//    protected Subscription subscription;
//
//    //构造方法私有
//
//    public HttpMethods(){
//        retrofit = new Retrofit.Builder()
//                .baseUrl(BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create(gson))
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                .client(okHttpClient())
//                .build();
//        restAPI = retrofit.create(RestAPI.class);
//    }
//
//    Gson gson = new GsonBuilder()
//            .setExclusionStrategies(new ExclusionStrategy() {
//                @Override
//                public boolean shouldSkipField(FieldAttributes f) {
//                    return f.getDeclaringClass().equals(RealmObject.class);
//                }
//
//                @Override
//                public boolean shouldSkipClass(Class<?> clazz) {
//                    return false;
//                }
//            })
//            .create();
//
//    private OkHttpClient okHttpClient(){
//        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
//        // config log
//        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
//        return new OkHttpClient.Builder()
//                .retryOnConnectionFailure(true) //设置出现错误进行重新连接。
//                .connectTimeout(15, TimeUnit.SECONDS)
//                .readTimeout(60 * 1000, TimeUnit.MILLISECONDS)
//                .addInterceptor(logging)   //拦截器
//                .build();
//
//    }
//
//    public RestAPI getGankService() {
//        return restAPI;
//    }
//    /**
//     * 被Dagger代替
//     */
//    //在访问HttpMethods时创建单例
//    private static class SingletonHolder{
//        private static final HttpMethods INSTANCE = new HttpMethods();
//    }
//
//    //获取单例
//    public static HttpMethods getInstance(){
//        return SingletonHolder.INSTANCE;
//    }
//
//
//    /**
//     * 用于获取Gank里新闻消息
//     * @param subscriber 由调用者传过来的观察者对象
//     *
//     */
//    public void getGanWu(Subscriber<List<Item>> subscriber){
//        KLog.a(restAPI);
//        unsubscribe();
//        Observable observable = restAPI.getGanWuData("2016","04","08")
//                .map(GanWuDataToItemsMapper.getInstance());
//        toSubscribe(observable, subscriber);
//    }
//
//    public void getImage(Subscriber<List<Image>> subscriber){
//        unsubscribe();
//        Observable observable = restAPI.getImageData(1)
//                .map(ImageToMeizhiMapper.getInstance());
//        toSubscribe(observable,subscriber);
//    }
//    protected void unsubscribe() {
//        if (subscription != null && !subscription.isUnsubscribed()) {
//            subscription.unsubscribe();
//        }
//    }
//    private <T> void toSubscribe(Observable<T> o, Subscriber<T> s){
//        subscription = o.subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(s);
//    }
//}
