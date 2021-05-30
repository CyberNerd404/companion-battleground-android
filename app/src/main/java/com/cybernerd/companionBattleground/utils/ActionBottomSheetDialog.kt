package com.cybernerd.companionBattleground.utils

import android.R
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.Nullable
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.bottom_sheet_layout.*
import java.lang.RuntimeException


class ActionBottomSheetDialog : BottomSheetDialogFragment(), View.OnClickListener {
    private var mListener: ItemClickListener? = null

    @Nullable
    override fun onCreateView(
        inflater: LayoutInflater, @Nullable container: ViewGroup?,
        @Nullable savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(com.cybernerd.companionBattleground.R.layout.bottom_sheet_layout, container, false)
    }

    override fun onViewCreated(view: View, @Nullable savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        set_home_screen.setOnClickListener(this)
        set_lock_screen.setOnClickListener(this)
        set_both_screen.setOnClickListener(this)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mListener = if (context is ItemClickListener) {
            context
        } else {
            throw RuntimeException(context.toString()
                .toString() + " must implement ItemClickListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    override fun onClick(view: View) {
        val tvSelected = view as TextView
        mListener!!.onItemClick(tvSelected.text.toString())
        dismiss()
    }

    interface ItemClickListener {
        fun onItemClick(item: String?)
    }

    companion object {
        const val TAG = "ActionBottomDialog"
        fun newInstance(): ActionBottomSheetDialog {
            return ActionBottomSheetDialog()
        }
    }
}