package com.xuanyuan.makefun.codingstudy;

/**
 * 作者：罗发新
 * 时间：2019/8/15 0015    星期四
 * 邮件：424533553@qq.com
 * 说明： Object类的研究
 */

public class ObjectStudy {

    /**
     * VM 本地方法
     * Causes the current thread to wait until another thread invokes the notify()
     * method or the notifyAll() method for this object.
     * 导致当前线程等待，直到另一个线程为此对象调用notify（）方法或notifyAll（）方法。
     * In other words, this method behaves exactly as if it simply
     * performs the call {@code wait(0)}.
     * 换句话说，此方法的行为就像它只是执行调用wait（0）一样。
     * <p>
     * The current thread must own this object's monitor. The thread releases ownership of this monitor
     * and waits until another thread notifies threads waiting on this object's monitor to wake up
     * either through a call to the {@code notify} method or the  notifyAll method.
     * 当前线程必须拥有此对象的监视器。 线程释放此监视器的所有权并等待，
     * 直到另一个线程通过调用{@code notify}方法或notifyAll方法通知等待此对象监视器的线程唤醒。
     * The thread then waits until it can re-obtain ownership of the monitor and resumes execution.
     * 然后线程等待，直到它可以重新获得监视器的所有权并继续执行。
     * <p>
     * As in the one argument version, interrupts and spurious wakeups are
     * possible, and this method should always be used in a loop:
     * 与在一个参数版本中一样，中断和虚假唤醒是可能的，并且此方法应始终在循环中使用：
     * <pre>
     *     synchronized (obj) {
     *         while (&lt;condition does not hold&gt;)
     *             obj.wait();
     *         ... // Perform action appropriate to condition
     *     }
     * </pre>
     * This method should only be called by a thread that is the owner of this object's monitor.See the notify
     * method for a description of the ways in which a thread can become the owner of a monitor.
     * 此方法只应由作为此对象监视器所有者的线程调用。请参阅notify方法，以获取有关线程可以成为监视器所有者的方式的说明。
     *
     * @throws IllegalMonitorStateException if the current thread is not the owner of the object's monitor.
     *                                      如果当前线程不是对象监视器的所有者。
     * @throws InterruptedException         if any thread interrupted the current thread before or while the current thread was waiting for a notification.
     *                                      The interrupted status of the current thread is cleared when  this exception is thrown.
     *                                      如果任何线程在当前线程等待通知之前或当前线程中断当前线程。 抛出此异常时，将清除当前线程的中断状态。
     * @see java.lang.Object#notify()
     * @see java.lang.Object#notifyAll()
     */
//    public final native void wait() throws InterruptedException;

    /**
     * Causes the current thread to wait until another thread invokes the  notify()
     * method or the notifyAll() method for this object, or some other thread interrupts
     * the current thread, or a certain amount of real time has elapsed.
     * 导致当前线程等待，直到另一个线程为此对象调用notify（）方法或notifyAll（）方法，
     * 或者某个其他线程中断当前线程，或者已经过了一定量的实时。
     * <p>
     * This method is similar to the {@code wait} method of one argument, but it allows
     * finer control over the amount of time to wait for a notification before giving up.
     * The amount of real time,measured in nanoseconds, is given by:
     * 此方法类似于一个参数的{@code wait}方法，但它允许更好地控制在放弃之前等待通知的时间量。
     * 以纳秒为单位的实时量由下式给出：
     *
     * <pre>
     * 1000000*timeout+nanos</pre></blockquote>
     * <p>
     * In all other respects, this method does the same thing as the method wait(long) of one argument.
     * In particular,wait(0, 0) means the same thing as wait(0).
     * 在所有其他方面，此方法与一个参数的wait（long）方法完全相同。 特别是，wait（0,0）表示与wait（0）相同的东西。
     *
     * <p>
     * The current thread must own this object's monitor. The thread releases ownership of this monitor
     * and waits until either of the following two conditions has occurred:
     * 当前线程必须拥有此对象的监视器。 该线程释放此监视器的所有权并等待，直到发生以下两种情况之一：
     * 1）Another thread notifies threads waiting on this object's monitor to wake up either through a call
     * to the  notify method or the notifyAll method.
     * 另一个线程通过调用notify方法或notifyAll方法通知等待此对象监视器上的线程唤醒。
     * 2）The timeout period, specified by timeout milliseconds plus {nanos} nanoseconds arguments, has elapsed.
     * 超时时间由超时毫秒+{nanos}纳秒参数指定 - 已经过去。
     * <p>
     * The thread then waits until it can re-obtain ownership of the monitor and resumes execution.
     * 然后线程等待，直到它可以重新获得监视器的所有权并继续执行。
     * <p>
     * As in the one argument version, interrupts and spurious wakeups are possible, and this method
     * should always be used in a loop:
     * 与在一个参数版本中一样，中断和虚假唤醒是可能的，并且这种方法
     * <pre>
     *     synchronized (obj) {
     *         while (&lt;condition does not hold&gt;)
     *             obj.wait(timeout, nanos);
     *         ... // Perform action appropriate to condition
     *     }
     * </pre>
     * This method should only be called by a thread that is the owner of this object's monitor.
     * See the {notify} method for a description of the ways in which a thread can become the owner of a monitor.
     * 此方法只应由作为此对象监视器所有者的线程调用。有关线程可以成为监视器所有者的方式的说明，请参阅{notify}方法。
     *
     * @param millis the maximum time to wait in milliseconds.等待的最长时间（以毫秒为单位）。
     * @param nanos  additional time, in nanoseconds range     额外的时间，以纳秒为单位
     *               0-999999.
     * @throws IllegalArgumentException     if the value of timeout is negative
     *                                      or the value of nanos is not in the range 0-999999.
     *                                      如果超时值为负或者nanos的值不在0-999999范围内。
     * @throws IllegalMonitorStateException if the current thread is not the owner of this object's monitor.
     *                                      如果当前线程不是此对象监视器的所有者。
     * @throws InterruptedException         if any thread interrupted the current thread before or while the current
     *                                      thread was waiting for a notification.The interrupted status of the current
     *                                      thread is cleared when  this exception is thrown.
     *                                      如果任何线程在当前线程等待通知之前或当前线程中断当前线程。
     *                                      当抛出此异常时，将清除当前线程的中断状态。
     */
    //public final native void wait(long millis, int nanos) throws InterruptedException;

}
