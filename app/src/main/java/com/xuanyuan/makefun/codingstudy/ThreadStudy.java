//package com.xuanyuan.makefun.codingstudy;
//
//import java.security.AccessController;
//
///**
// * 作者：罗发新
// * 时间：2019/8/14 0014    星期三
// * 邮件：424533553@qq.com
// * 说明：进行Thread源码的研究
// */
//public class ThreadStudy {
//
//    /**
//     * Allocates a new  Thread object. This constructor has the same effect as Thread(ThreadGroup,Runnable,
//     * String Thread, where {@code gname} is a newly generated name.
//     * 分配一个新的Thread对象。 此构造函数与Thread（ThreadGroup，Runnable，String Thread）具有相同的效果，
//     * 其中{@code gname}是新生成的名称。
//     * Automatically generated names are of the form "Thread-"+n, where n is an integer.
//     * 自动生成的名称的格式为“Thread  - ”+ n，其中n是整数。
//     */
//    public ThreadStudy() {
//        init(null, null, "Thread-" + nextThreadNum(), 0);
//    }
//
//    /**
//     * @param target the object whose {@code run} method is invoked when this thread is started.
//     *               启动此线程时调用其{@code run}方法的对象。
//     *               If {@code null}, this classes {@code run} method does nothing.
//     *               如果{@code null}，则此类{@code run}方法不执行任何操作。
//     */
//    public ThreadStudy(Runnable target) {
//        init(null, target, "Thread-" + nextThreadNum(), 0);
//    }
//
//    /**
//     * @param name the name of the new thread  给创建的thread 自定义名字
//     */
//    public ThreadStudy(String name) {
//        init(null, null, name, 0);
//    }
//
//    private static synchronized int nextThreadNum() {
//        /* threadInitNumber ： For autonumbering anonymous threads. 用于自动编号匿名线程，也是永久自增长的 */
//        return threadInitNumber++;
//    }
//
//    /**
//     * If this thread was constructed using a separate Runnable run object,then that Runnable object's run method is called;
//     * 如果使用单独的Runnable运行对象构造此线程，则调用该Runnable对象的run方法;
//     * otherwise, this method does nothing and returns.
//     * 否则，此方法不执行任何操作并返回。
//     *
//     * Subclasses of Thread should override this method.
//     * Thread的子类应该重写此方法。
//     *
//     * @see     #start()
//     * @see     #stop()
//     * @see     #Thread(ThreadGroup, Runnable, String)
//     */
//    public void run() {
//        if (target != null) {
//            target.run();
//        }
//    }
//
//
//    /**
//     * Forces the thread to stop executing.
//     * 强制线程停止执行
//     *
//     * If there is a security manager installed, its checkAccess method is called with this as its argument.
//     * 如果安装了安全管理器，则调用其checkAccess方法，并将此参数作为其参数。
//     *
//     * This may result in a SecurityException being raised (in the current thread).
//     * 这可能会导致引发SecurityException（在当前线程中）。
//     * <p>
//     *
//     * If this thread is different from the current thread (that is, the current thread is trying to stop a thread other than itself),
//     * 如果此线程与当前线程不同（即，当前线程试图阻止除自身之外的线程），
//     * the security manager's checkPermission method (with a RuntimePermission("stopThread") argument)is called in addition.
//     * 另外调用安全管理器的checkPermission方法（带有RuntimePermission（“stopThread”）参数）。
//     * Again, this may result in throwing a SecurityException (in the current thread).
//     * 同样，这可能会导致抛出SecurityException（在当前线程中）。
//     * <p>
//     * The thread represented by this thread is forced to stop whatever it is doing abnormally
//     * and to throw a newly created ThreadDeath object as an exception.
//     * 该线程表示的线程被强制停止异常处理，并将新创建的ThreadDeath对象作为异常抛出。
//     * <p>
//     * It is permitted to stop a thread that has not yet been started.
//     * If the thread is eventually started, it immediately terminates.
//     * 允许停止尚未启动的线程。 如果线程最终启动，它会立即终止。
//     *
//     * An application should not normally try to catch ThreadDeath unless it must do some extraordinary cleanup operation(note that
//     * the throwing of ThreadDeath causes finally clauses of try statements to be executed before the thread officially dies).
//     * If a catch clause catches a ThreadDeath object,it is important to rethrow the object so that the thread actually dies.
//     * 应用程序通常不应该尝试捕获ThreadDeath，除非它必须执行一些特殊的清理操作（请注意，抛出ThreadDeath会导致try语句的
//     * finally子句在线程正式死亡之前执行）。如果catch子句捕获ThreadDeath对象，则重新抛出对象以使线程实际死亡是很重要的。
//     * <p>
//     * The top-level error handler that reacts to otherwise uncaught exceptions does not print out a message
//     * or otherwise notify the application if the uncaught exception is an instance of ThreadDeath.
//     * 如果未捕获的异常是ThreadDeath的实例，则对其他未捕获的异常作出反应的顶级错误处理程序不会打印出消息或以其他方式通知应用程序。
//     * @exception  SecurityException  if the current thread cannot modify this thread.
//     * @see        #interrupt()
//     * @see        #checkAccess()
//     * @see        #run()
//     * @see        #start()
//     * @see        ThreadDeath
//     * @see        ThreadGroup#uncaughtException(Thread,Throwable)
//     * @see        SecurityManager#checkAccess(Thread)
//     * @see        SecurityManager#checkPermission
//     * @deprecated
//     * This method is inherently unsafe.  Stopping a thread with  Thread.stop causes it to unlock
//     * all of the monitors that it has locked (as a natural consequence of the unchecked ThreadDeath
//     * exception propagating up the stack).
//     * 这种方法本质上是不安全的。 使用Thread.stop停止一个线程会导致它解锁它已锁定的所有监视器
//     * （这是未经检查的ThreadDeath异常向上传播的自然结果）。
//     * If any of the objects previously protected by these monitors were in an inconsistent state,
//     * the damaged objects become visible to other threads, potentially resulting in arbitrary behavior.
//     * Many uses of stop should be replaced by code that simply modifies some variable to indicate that
//     * the target thread should stop running.
//     * 如果先前受这些监视器保护的任何对象处于不一致状态，则受损对象对其他线程可见，可能导致任意行为。
//     * stop的许多用法应该被简单修改某个变量的代码替换，以指示目标线程应该停止运行。
//     *
//     * The target thread should check this variable regularly, and return from its run method in an orderly
//     * fashion if the variable indicates that it is to stop running.  If the target thread waits for long
//     * periods (on a condition variable,for example), the interrupt method should be used to interrupt the wait.
//     * 目标线程应该定期检查此变量，并且如果变量指示它将停止运行，则以有序的方式从其run方法返回。
//     * 如果目标线程等待很长时间（例如，在条件变量上），则应使用中断方法来中断等待。
//     *       For more information, see
//     *       <a href="{@docRoot}openjdk-redirect.html?v=8&path=/technotes/guides/concurrency/threadPrimitiveDeprecation.html">
//     *           Why are Thread.stop, Thread.suspend and Thread.resume Deprecated?</a>.
//     */
//    @Deprecated
//    public final void stop() {
//        stop(new ThreadDeath());
//    }
//
//
//
//    /**
//     * Throws {@code UnsupportedOperationException}.
//     * //不支持的操作异常
//     *
//     * @deprecated This method was originally designed to force a thread to stop
//     *        and throw a given {@code Throwable} as an exception. It was
//     *        inherently unsafe (see {@link #stop()} for details), and furthermore
//     *        could be used to generate exceptions that the target thread was
//     *        not prepared to handle.
//     *        For more information, see
//     *        <a href="{@docRoot}openjdk-redirect.html?v=8&path=/technotes/guides/concurrency/threadPrimitiveDeprecation.html">Why
//     *        are Thread.stop, Thread.suspend and Thread.resume Deprecated?</a>.
//     */
//    @Deprecated
//    public final void stop(Throwable obj) {
//        throw new UnsupportedOperationException();
//    }
//
//    /**
//     * Initializes a Thread.  初始化一个线程
//     *
//     * @param g         the Thread group  线程组,默认与创建者线程的group一致
//     * @param target    the object whose run() method gets called  调用run的runnale ,默认null
//     * @param name      the name of the new Thread     线程名字，默认会自动创建  ,默认Thread-n，系统自增
//     * @param stackSize the desired stack size for the new thread, or
//     *                  zero to indicate that this parameter is to be ignored.
//     *                  新线程所需的堆栈大小，或者为零以指示要忽略此参数 。
//     *                  默认为0，由native方法自动决定堆栈大小
//     */
//    private void init(ThreadGroup g, Runnable target, String name, long stackSize) {
//        //获取正在执行的线程， currentThread()是一个VM中的方法
//        Thread parent = currentThread();
//        if (g == null) {
//            //默认传值g为null,即新建的thread和正在执行的Thread将在同一个ThreadGroup
//            g = parent.getThreadGroup();
//        }
//
//        g.addUnstarted();
//        this.group = g;
//
//        this.target = target;
//        // 创建者和被创建者的优先级是一样的，
//        this.priority = parent.getPriority();
//        // 创建它的线程是否是守护线程
//        this.daemon = parent.isDaemon();
//        setName(name);
//
//        // 这波操作暂时看不懂
//        init2(parent);
//
//        /* Stash the specified stack size in case the VM cares */
//        this.stackSize = stackSize;
//        tid = nextThreadID();
//    }
//
//    /**
//     * @return 返回生成的线程id ,一直永久递增的。
//     * 想想开机很久很久，创建很多线程后，是不是tid会越界呢
//     */
//    private static synchronized long nextThreadID() {
//        /* threadSeqNumber : For generating thread ID */
//        return ++threadSeqNumber;
//    }
//
//    /*
//     * InheritableThreadLocal values pertaining to this thread. This map is
//     * maintained by the InheritableThreadLocal class.
//     * 与此线程相关的InheritableThreadLocal值。 此映射由InheritableThreadLocal类维护。
//     */
//    ThreadLocal.ThreadLocalMap inheritableThreadLocals = null;
//
//    private void init2(Thread parent) {
//        this.contextClassLoader = parent.getContextClassLoader();
//        this.inheritedAccessControlContext = AccessController.getContext();
//        if (parent.inheritableThreadLocals != null) {
//            this.inheritableThreadLocals = ThreadLocal.createInheritedMap(
//                    parent.inheritableThreadLocals);
//        }
//    }
//
//    /**
//     * Returns the context ClassLoader for this Thread. The context
//     * ClassLoader is provided by the creator of the thread for use
//     * by code running in this thread when loading classes and resources.
//     * If not {@linkplain #setContextClassLoader set}, the default is the
//     * ClassLoader context of the parent Thread. The context ClassLoader of the
//     * primordial thread is typically set to the class loader used to load the
//     * application.
//     *
//     * <p>If a security manager is present, and the invoker's class loader is not
//     * {@code null} and is not the same as or an ancestor of the context class
//     * loader, then this method invokes the security manager's {@link
//     * SecurityManager#checkPermission(java.security.Permission) checkPermission}
//     * method with a {@link RuntimePermission RuntimePermission}{@code
//     * ("getClassLoader")} permission to verify that retrieval of the context
//     * class loader is permitted.
//     *
//     * @return the context ClassLoader for this Thread, or {@code null}
//     * indicating the system class loader (or, failing that, the
//     * bootstrap class loader)
//     * @throws SecurityException //如果该线程不能得到ClassLoader 将抛出安全异常
//     *                           if the current thread cannot get the context ClassLoader
//     */
//    public ClassLoader getContextClassLoader() {
//        return contextClassLoader;
//    }
//
//    /**
//     * Causes this thread to begin execution; the Java Virtual Machine
//     * calls the run method of this thread.
//     * 导致此线程开始执行; Java JVM  调用此线程的run方法。
//     * The result is that two threads are running concurrently: the
//     * current thread (which returns from the call to the start method) and
//     * the other thread (which executes its run method).
//     * 结果是两个线程并发运行：当前线程（从调用start方法返回）和另一个线程（执行其run方法）。
//     * It is never legal to start a thread more than once.
//     * 多次调用 线程的start()方法是不合法的
//     * In particular, a thread may not be restarted once it has completed execution.
//     * 一旦线程执行完成，将无法再次start()
//     *
//     * @throws IllegalThreadStateException if the thread was already started. 非法的线程状态异常
//     *                                     <p>
//     */
//    public synchronized void start() {
//        /**
//         * This method is not invoked for the main method thread or "system" group threads
//         * created/set up by the VM. Any new functionality added  to this method in the future
//         * may have to also be added to the VM.
//         * 该方法不会被 VM创建的 主线程或者系统组线程所调用，
//         * 该方法中添加的任何新的功能在将来可能不得不添加到VM中去
//         *
//         * threadStatus 默认值：  private volatile int threadStatus = 0;
//         * initialized to indicate thread 'not yet started'
//         * A zero status value corresponds to state "NEW".
//         * threadStatus：零状态值对应于状态“NEW”。初始化为0标示线程还未启动
//         */
//        // Android-changed: throw if 'started' is true
//        // 如果线程已被启动 或者start()方法已被调用过，则抛出IllegalThreadStateException
//        if (threadStatus != 0 || started)
//            throw new IllegalThreadStateException();
//
//        /* Notify the group that this thread is about to be started
//         * so that it can be added to the group's list of threads
//         * and the group's unstarted count can be decremented. */
//        // 通知线程组这个线程将start，以便线程组将线程加入线程组的队列中，
//        // 此时未启动计数器-1。线程组中的已启动和未启动计数器是会一一对应正确计数
//        group.add(this);
//        started = false;
//        try {
//            //VM中的方法
//            nativeCreate(this, stackSize, daemon);
//            //标记线程启动了
//            started = true;
//        } finally {
//            try {//nativeCreate调用失败，比如内存不够，线程数量超过限制等原因
//                if (!started) {
//                    // 线程调用失败, 1)从线程组中移除该线程； 2）unStart计数器-1
//                    group.threadStartFailed(this);
//                }
//            } catch (Throwable ignore) {
//                    /* do nothing. If start0 threw a Throwable then
//                      it will be passed up the call stack */
//            }
//        }
//    }
//
//    /**
//     * 此处是VM中的方法，最终调用了线程中的run方法。 //
//     *
//     * @param t         本身
//     * @param stackSize 请求的堆栈大小，当传入为0时，虚拟机会自动分配一定的大小给新的线程，
//     *                  根据不同的VM的内存大小和参数，分配的大小可能不一样
//     *                  The requested stack size for this thread, or 0 if the creator did not specify a stack size.
//     *                  It is up to the VM to do whatever it likes with this number; some VMs will ignore it.
//     *                  此线程请求的堆栈大小，如果创建者未指定堆栈大小，则为0。
//     *                  虚拟机可以用这个号码做任何喜欢的事情; 一些虚拟机会忽略它。
//     * @param daemon    是否为守护线程
//     *                  Whether or not the thread is a daemon thread
//     *                  是否为守护线程
//     */
//    private native static void nativeCreate(Thread t, long stackSize, boolean daemon);
//
//    /**
//     * Returns a reference to the currently executing thread object.
//     * 返回对当前正在执行的线程对象的引用。
//     *
//     * @return the currently executing thread.
//     * 当前正在执行的线程。
//     */
//    public static native Thread currentThread();
//
//    // 使用情况，测试结果。
//
//}
