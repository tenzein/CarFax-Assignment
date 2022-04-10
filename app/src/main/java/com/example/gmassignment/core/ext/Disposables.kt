package com.example.gmassignment.core.ext

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


/**
 * Created by TENZING SHERPA on 4/2/22.
 * Email: tenzingherpaaa@gmail.com
 */
@JvmName("addToComposite")
fun Disposable.addTo(disposableComposite: CompositeDisposable) {
    disposableComposite.add(this)
}