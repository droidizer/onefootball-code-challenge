package com.onefootball.util

import com.onefootball.ui.util.schedulers.ISchedulersProvider
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class TestSchedulerProvider : ISchedulersProvider {
    override fun getComputationScheduler(): Scheduler = Schedulers.trampoline()

    override fun getIOScheduler(): Scheduler = Schedulers.trampoline()

    override fun getUIScheduler(): Scheduler = Schedulers.trampoline()

    override fun getNewThreadScheduler() = Schedulers.trampoline()
}