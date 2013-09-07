Unique Tracker
==============
Creates a date-stamped entry for uniques and saves them to a log file, allowing tracking of unique acquisition.

To use, select item base group in the first combo box, specific item category in the second combo box (or, if weapon
is selected as base type, wield type (one- or two-handed) in the second combo box and specific item category in the
second combo box), and specific item in the final combo box.

Hit "Add Unique" button to add selected unique item to the list.

"Save List" button saves the current list, and "Load List" reloads the list from the log file - only necessary to undo
a change, as program loads the log file by default on launch.


Notes
=====
Uses joda to generate date stamp.

By default, displays in middle of screen on second monitor.  MidScreenFrame.class can be edited for specific monitor
choice or exact location - as of current version, these are only changeable within the class files, but this will be
changed in future versions.
