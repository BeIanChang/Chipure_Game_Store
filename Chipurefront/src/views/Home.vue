<!--
 * @Description: 首页组件
 -->
<template>
  <div class="home" id="home" name="home">
    <!-- 轮播图 -->
    <div class="block">
      <el-carousel height="400px">
        <el-carousel-item v-for="item in carousel" :key="item.carousel_id">
          <img style="height:400px;width:940px" :src="$target + item.imgPath" :alt="item.describes" />
        </el-carousel-item>
      </el-carousel>
    </div>
    <!-- 轮播图END -->
    <div class="main-box">
      <div class="main">

        <div class="playrole">
          <div class="box-hd">
            <div class="title">角色扮演</div>
          </div>
          <div class="box-bd">
            <div class="promo-list">
              <router-link to>
                <img style="height:300px" :src="$target +'public/gameimg/mhw.jpg'" />
              </router-link>
            </div>
            <div class="list">
              <MyList :list="playroleGameList" :isMore="true"></MyList>
            </div>
          </div>
        </div>



        <div class="actiongame" id="promo-menu">
          <div class="box-hd">
            <div class="title">动作冒险</div>
            <div class="more" id="more">
            </div>
          </div>
          <div class="box-bd">
            <div class="promo-list">
              <ul>
                <li>
                  <img style="height:300px" :src="$target +'public/gameimg/spotlight_image_english.jpg'" />
                </li>
              </ul>
            </div>
            <div class="list">
              <MyList :list="actionGameList" :isMore="true"></MyList>
            </div>
          </div>
        </div>



        <div class="openworld" id="promo-menu">
          <div class="box-hd">
            <div class="title">开放世界</div>
            <div class="more" id="more">
            </div>
          </div>
          <div class="box-bd">
            <div class="promo-list">
              <ul>
                <li>
                  <img style="height:300px" :src="$target +'public/gameimg/zelda.jpg'" alt />
                </li>
              </ul>
            </div>
            <div class="list">
              <MyList :list="OpenworldList" :isMore="true"></MyList>
            </div>
          </div>
        </div>

      </div>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      carousel: "", // 轮播图数据
      playroleGameList: "", // 角色扮演游戏列表
      actionGameList: "", // 动作冒险游戏列表
      OpenworldList: "", //开放世界游戏列表
    };
  },

  created() {
    // 获取轮播图数据
    this.$axios
      .post("/api/resources/carousel",{})
        // .post("/api/carousel/getCarousel",{})
      .then(res => {
        this.carousel = res.data.carousel;
      })
      .catch(err => {
        return Promise.reject(err);
      });
    // 获取各类商品数据
    this.getPromo("角色扮演", "playroleGameList");
    this.getPromo("动作冒险", "actionGameList");
    this.getPromo("开放世界", "OpenworldList");
  },
  methods: {

    // 获取各类商品数据方法封装
    getPromo(categoryName, val, api) {
      api = api != undefined ? api : "/api/product/getPromoProduct";
      this.$axios
        .post(api, {
          categoryName
        })
        .then(res => {
          this[val] = res.data.Product;
        })
        .catch(err => {
          return Promise.reject(err);
        });
    }
  }
};
</script>
<style scoped>
@import "../assets/css/index.css";
</style>