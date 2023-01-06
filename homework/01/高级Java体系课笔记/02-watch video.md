
# 分布式压测


## 08 video
install openresty to linux server 

## 10 JVM optimisation

10.1. why need it
	 - to offer higher throughput with less hardware capacity
	 - 用最小的硬件消耗来正在更大的吞吐量
	 - cost deduction - career growth point

10.2. when to perform
	- 系统吞吐量下降
	- 相应性能不高
	- heap 过高
	- full gc过多
	- gc 停顿时间过长
	- outofmemory错误处出现
	- 本地缓存占用了大量的内存

10.3. 调优原则
	- 优先原则
		- 优先架构调优和代码调优
		- JVM是最后的手段
		- 大多数不需要JVM调优
	- 观测原则
		- 没问题不要调

