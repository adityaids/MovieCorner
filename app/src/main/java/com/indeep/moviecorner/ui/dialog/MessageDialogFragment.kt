package com.indeep.moviecorner.ui.dialog

import android.R
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class MessageDialogFragment : DialogFragment() {
    companion object {
        private const val ARG_TITLE_ID = "title_id"
        private const val ARG_MESSAGE = "message"
        fun newInstance(@StringRes titleId: Int, message: String?): DialogFragment {
            val fragment: DialogFragment = MessageDialogFragment()
            val arguments = Bundle()
            arguments.putInt(ARG_TITLE_ID, titleId)
            arguments.putString(ARG_MESSAGE, message)
            fragment.arguments = arguments
            return fragment
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        @StringRes val title = requireArguments().getInt(ARG_TITLE_ID)
        val message = requireArguments().getString(ARG_MESSAGE)
        val dialogBuilder = AlertDialog.Builder(
            requireActivity()
        )
            .setMessage(message)
            .setCancelable(true)
            .setPositiveButton(
                R.string.ok
            ) { _: DialogInterface?, _: Int -> dismiss() }
        if (title != 0) {
            dialogBuilder.setTitle(title)
        }
        return dialogBuilder.create()
    }
}