# ItemAnimationBlobkClick

这是一个在 RecyclerView 的 change 动画过程中，响应点击事件的演示demo。

具体描述参见[我的博客文章](https://xiaozhikang0916.github.io/blog/recycler-animator-click/)。

## 使用简介

项目中使用了两个 Fragment 分别展现有问题的 RecyclerView 动画，和修复后的动画。

### Default

在页面底部点击 `Default` 按钮可以显示原本有问题的页面，点击一个 `Check` 按钮后，对应列表项将会开始替换动画（动画时间被延长到 1.5s 方便观察）；在动画过程中再次点击同一个按钮，将会看到一个 `error` 的 log 输出，因为传入的状态与数据中存有的状态不符，并且按钮动画消失，马上转变回原有的样子。

### Click

`Click` 按钮可以显示经过修复的页面，重复上述操作会发现在动画过程中，重复的点击事件不会被响应，也不会看到 `error` 的 log 输出，直到动画播放完成后才能正确响应下一次点击。

### Tag

`Tag` 按钮可以显示使用另一种方式修复的页面，其表现跟 Click 页面没有区别。