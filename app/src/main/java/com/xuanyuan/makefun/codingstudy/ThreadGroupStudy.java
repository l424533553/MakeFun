//package com.xuanyuan.makefun.codingstudy;
//
///**
// * 作者：罗发新
// * 时间：2019/8/14 0014    星期三
// * 邮件：424533553@qq.com
// * 说明：线程组的学习
// */
//
///**
// * ThreadGroup  group  解释：
// * The locking strategy for this code is to try to lock only one level of the
// * tree wherever possible, but otherwise to lock from the bottom up.
// * 这段代码的锁定策略是尝试尽可能只锁定树的一个级别，否则从底部向上锁定。
// * 。 此策略通常会导致获取线程组状态的快照并处理该快照，而不是在我们处理子项时锁定线程组。
// * <p>
// * <p>
// * That is, from child thread groups to parents. 也就是说，从子线程组到父级
// * This has the advantage of limiting the number of locks that need to be held and in particular
// * avoids having to grab the lock for the root thread group,(or a global lock) which would be
// * a source of contention on a multi-processor system with many thread groups.
// * 这样做的好处是限制那些需要hold的锁的数量，并且特别注意尽量避免获取根线程组（或全局锁）的锁，
// * 在多处理器的系统中这将是大量线程组争夺的资源
// * This policy often leads to taking a snapshot of the state of a thread group and working off of that
// * snapshot, rather than holding the thread group locked while we work on the children.
// * 此策略通常会导致 获取线程组状态的快照并处理该快照，而不是在我们处理子项时锁定线程组。
// */
//public class ThreadGroupStudy extends ThreadGroup {
//
//    public ThreadGroupStudy(String name) {
//        super(name);
//    }
//
//    public ThreadGroupStudy(ThreadGroup parent, String name) {
//        super(parent, name);
//    }
//
//    /**
//     * ThreadGroup中的方法
//     *
//     * Notifies the group that the thread {@code t} has failed an attempt to start.
//     * 通知线程组该线程尝试start失败
//     *
//     * The state of this thread group is rolled back as if the attempt to start the thread has never occurred.
//     * 回滚此线程组的状态，就好像从未发生过启动线程的尝试一样。
//     * The thread is again considered an unstarted member of the thread group,
//     * and a subsequent attempt to start the thread is permitted.
//     * 该线程再次被视为线程组的未启动成员，并允许后续尝试启动该线程。
//     *
//     * @param  t  调用start()的线程
//     *         the Thread whose start method was invoked
//     */
//    void threadStartFailed(Thread t) {
//        synchronized(this) {
//            remove(t);
//            //unStart计数器+1
//            nUnstartedThreads++;
//        }
//    }
//
//    /**
//     * Removes the specified Thread from this group.
//     * 从该组中删除指定的线程。
//     * Invoking this method a thread group that has been destroyed has no effect.
//     * 在已销毁的线程组上调用此方法无效。
//     * @param  t  将要移除的thread
//     *         the Thread to be removed
//     */
//    private void remove(Thread t) {
//        synchronized (this) {
//            if (destroyed) {
//                return;
//            }
//            for (int i = 0 ; i < nthreads ; i++) {
//                if (threads[i] == t) {
//                    System.arraycopy(threads, i + 1, threads, i, --nthreads - i);
//                    // Zap dangling reference to the dead thread so that
//                    // the garbage collector will collect it.
//                    threads[nthreads] = null;
//                    break;
//                }
//            }
//        }
//    }
//
//    /**
//     * Increments the count of unstarted threads in the thread group.
//     * 增加线程组中未启动线程的数量。
//     * Unstarted threads are not added to the thread group so that they can be collected if they are never started,
//     * 未启动的线程没有加入到线程组中， 以便在它们被收集统计如果它们永远没有被启动 ，
//     * but they must be counted so that daemon thread groups with unstarted threads in them are not destroyed.
//     * 但必须对它们进行计数，以便有未启动线程的 守护程序线程组不被破坏。
//     */
//    void addUnstarted() {
//        synchronized(this) {
//            if (destroyed) {
//                throw new IllegalThreadStateException();
//            }
//            nUnstartedThreads++;
//        }
//    }
//}
