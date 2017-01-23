# 如何优雅为recyclerview设置多种布局

> 要优雅就要符合 开闭原则，单一职责，当增加新的类型事只能扩展不能修改源代码。

* 每增加一种类型view时需要新增一个ItemViewFactory的直接子类，或者间接子类

* 通过调用MultiTypeRecyclerAdapter的setData或appendData控制view显示顺序

* 可以通过调用MultiTypeRecyclerAdapter的setTypeMapPolicy为adapter设置类型映射策略，默认使用DefaultTypeMapPolicy

* 具体用法参考工程中 MainActivity
