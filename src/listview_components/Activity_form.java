package listview_components;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import resources.Activities;
import resources.Users;
import saver_loader.DataResource;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.border.BevelBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JButton;

import domain.ActivityController;

@SuppressWarnings("serial")
public class Activity_form extends JFrame {

	private JPanel contentPane;
	private JTextField descriptionField;
	private JTextField startField;
	private JTextField endField;
	private JTextField activityLabelField;

	private ArrayList<String> dependencies = new ArrayList<String>();
	private ArrayList<String> members = new ArrayList<String>();

	public Activity_form() {

		// Initialize JFrame Settings
		setTitle("ACTIVITY CREATION");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 266, 455);
		contentPane = new JPanel();
		contentPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Create and add Description Field
		descriptionField = new JTextField();
		descriptionField.setBounds(116, 23, 124, 20);
		descriptionField.setColumns(10);
		contentPane.add(descriptionField);

		// Create and add all Labels
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setBounds(21, 26, 58, 14);
		contentPane.add(lblDescription);

		JLabel lblStart = new JLabel("Start Date (DD-MM-YYYY)");
		lblStart.setBounds(21, 64, 46, 14);
		contentPane.add(lblStart);

		JLabel lblEnd = new JLabel("End Date (DD-MM-YYYY)");
		lblEnd.setBounds(21, 94, 46, 14);
		contentPane.add(lblEnd);

		JLabel lblLabel = new JLabel("Label");
		lblLabel.setBounds(21, 123, 46, 14);
		contentPane.add(lblLabel);

		JLabel lblDependencies = new JLabel("Dependencies");
		lblDependencies.setBounds(21, 157, 80, 14);
		contentPane.add(lblDependencies);

		JLabel lblResources = new JLabel("Resources");
		lblResources.setBounds(21, 267, 80, 14);
		contentPane.add(lblResources);

		// Create and add all text Fields
		startField = new JTextField();
		startField.setBounds(116, 61, 124, 20);
		contentPane.add(startField);
		startField.setColumns(10);

		// Create and add all text Fields
		endField = new JTextField();
		endField.setBounds(116, 91, 124, 20);
		contentPane.add(endField);
		endField.setColumns(10);

		activityLabelField = new JTextField();
		activityLabelField.setBounds(116, 120, 58, 20);
		contentPane.add(activityLabelField);
		activityLabelField.setColumns(10);

		// Create an array of the current Activities
		int activityCount = DataResource.selectedProject.getActivityList().size();
		Activities[] activityList = new Activities[activityCount];
		DataResource.selectedProject.getActivityList().toArray(activityList);

		// Create Selections from the list of Activities and their labels
		String[] selections = new String[activityCount];

		for (int i = 0; i < activityCount; i++)
			selections[i] = activityList[i].getLabel();

		// Create list with selections
		final JList<String> selectionList = new JList<String>(selections);

		// Create ScrollPane with list inside and add to Frame
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPane_1.setBounds(116, 157, 101, 88);
		scrollPane_1.setViewportView(selectionList);

		contentPane.add(scrollPane_1);

		String[] memberNames = new String[DataResource.projectMembers.size()];

		for (int i = 0; i < DataResource.projectMembers.size(); i++) {
			memberNames[i] = DataResource.projectMembers.get(i).getName();
		}
		final JList<String> memberList = new JList<String>(memberNames);
		// Create ScrollPane with list inside and add to Frame
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPane_2.setBounds(116, 267, 101, 88);
		scrollPane_2.setViewportView(memberList);

		contentPane.add(scrollPane_2);

		// Initialize and set Buttons
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(28, 372, 89, 23);
		contentPane.add(btnCancel);

		JButton btnSave = new JButton("Save");
		btnSave.setBounds(138, 372, 89, 23);
		contentPane.add(btnSave);

		// Add and define ActionListeners to the buttons

		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				disposeWindow();
			}
		});

		btnSave.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ActivityController.addActivity(descriptionField.getText(), startField.getText(), endField.getText(), activityLabelField.getText(), dependencies, members);
				disposeWindow();
			}
		});

		// Create the listListener for dependency choices
		selectionList.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {

				if (e.getValueIsAdjusting()) {// This line prevents double
												// events

					dependencies = (ArrayList<String>) selectionList.getSelectedValuesList();

				}
			}
		});

		// Create the listListener for member choices
		memberList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting()) {// This line prevents double
												// events
					members = (ArrayList<String>) memberList.getSelectedValuesList();
				}
			}
		});

	}

	private void disposeWindow() {
		this.dispose();
	}

}
