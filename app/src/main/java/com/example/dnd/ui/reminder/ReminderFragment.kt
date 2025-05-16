package com.example.dnd.ui.reminder

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationManagerCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil3.load
import coil3.request.error
import coil3.request.placeholder
import com.example.dnd.R
import com.example.dnd.databinding.ReminderFragmentBinding
import com.google.android.material.snackbar.Snackbar

class ReminderFragment : Fragment() {

    private lateinit var binding: ReminderFragmentBinding
    private lateinit var viewModel: ReminderViewModel
    private val permission = Manifest.permission.POST_NOTIFICATIONS
    private val launcher =
        this.registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            onRequestPermissionsResult(it)
        }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater, R.layout.reminder_fragment, container, false
        )

        viewModel = ViewModelProvider(this).get(ReminderViewModel::class.java)

        binding.reminder = viewModel
        binding.lifecycleOwner = this.viewLifecycleOwner

        createChannel(
            getString(R.string.reminder_channel_id),
            getString(R.string.reminder_channel_name)
        )

        binding.onOffSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                requestPermission()
            } else viewModel.setAlarm(false)
        }
        binding.reminderImage.load(
            "https://f000.backblazeb2.com/file/dnd-udacity/reminder_image.png",
            builder = {
                placeholder(R.drawable.image_placeholder)
                error(R.drawable.image_placeholder)
            })

        return binding.root
    }

    private fun createChannel(channelId: String, channelName: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                channelId,
                channelName,
                NotificationManager.IMPORTANCE_HIGH
            )
                .apply {
                    setShowBadge(false)
                }

            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.RED
            notificationChannel.enableVibration(true)
            notificationChannel.description = getString(R.string.notification_title)

            val notificationManager = requireActivity().getSystemService(
                NotificationManager::class.java
            )
            notificationManager.createNotificationChannel(notificationChannel)

        }
    }


    private fun requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (
                PackageManager.PERMISSION_GRANTED ==
                ActivityCompat.checkSelfPermission(
                    this.requireContext(),
                    permission
                )
            )
                viewModel.setAlarm(true)
            else {
                launcher.launch(permission)
                return
            }
        } else {
            checkAndRequestNotificationPermission()
        }
    }

    private fun checkAndRequestNotificationPermission() {
        val areNotificationsEnabled =
            NotificationManagerCompat.from(requireContext()).areNotificationsEnabled()

        if (!areNotificationsEnabled) {
            viewModel.setAlarm(false)
            val intent = Intent().apply {
                action = Settings.ACTION_APP_NOTIFICATION_SETTINGS
                putExtra(Settings.EXTRA_APP_PACKAGE, requireContext().packageName)
            }
            requireActivity().startActivity(intent)
        } else {
            viewModel.setAlarm(true)
        }
    }

    private fun onRequestPermissionsResult(
        permissionsResult: Boolean
    ) {
        if (
            PackageManager.PERMISSION_GRANTED ==
            ActivityCompat.checkSelfPermission(
                this.requireContext(),
                permission
            )
        ) viewModel.setAlarm(true)
        else {
            Snackbar.make(
                this.requireView(),
                R.string.permission_denied_explanation,
                Snackbar.LENGTH_SHORT
            )
                .setAction(R.string.settings) {
                    this.startActivity(Intent().apply {
                        action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                        data = Uri.fromParts("package", requireContext().packageName, null)
                        flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    })
                }.show()
            return
        }
    }

    companion object {
        fun newInstance() = ReminderFragment()
    }
}