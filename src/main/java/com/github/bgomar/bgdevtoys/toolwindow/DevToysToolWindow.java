package com.github.bgomar.bgdevtoys.toolwindow;

import com.intellij.openapi.util.IconLoader;
import com.intellij.ui.ComboboxSpeedSearch;
import com.intellij.ui.components.JBRadioButton;
import com.intellij.ui.components.JBTextField;
import com.github.bgomar.bgdevtoys.toolwindow.setup.*;

import javax.swing.*;
import java.util.LinkedHashMap;

public class DevToysToolWindow {

    private JPanel mainPanel;
    private JComboBox<ComboBoxWithImageItem> toolComboBox;
    private JLabel helpLabel;

    private JPanel base64Panel;
    private JBRadioButton base64RadioButtonUTF8;
    private JBRadioButton base64RadioButtonASCII;
    private JTextArea base64RawTextArea;
    private JTextArea base64Base64TextArea;

    private JPanel urlCodecPanel;
    private JBTextField urlCodecDecodedTextField;
    private JBTextField urlCodecEncodedTextField;

    private JPanel loremIpsumPanel;
    private JButton loremIpsumGenerateButton;
    private JTextArea loremIpsumTextArea;

    private JPanel hashPanel;
    private JTextArea hashInputTextArea;
    private JBTextField hashMD5TextField;
    private JBTextField hashSHA1TextField;
    private JBTextField hashSHA256TextField;
    private JBTextField hashSHA384TextField;
    private JBTextField hashSHA512TextField;
    private JBTextField hashBCrypt2ATextField;
    private JBTextField hashBCrypt2BTextField;
    private JBTextField hashBCrypt2YTextField;

    private JPanel uuidPanel;
    private JButton uuidGenerateButton;
    private JTextArea uuidTextArea;

    private JPanel passwordStrengthPanel;
    private JTextField passwordStrengthPasswordTextField;
    private JTextArea passwordStrengthReportTextArea;

    private JPanel timestampPanel;
    private JComboBox<ComboBoxWithImageItem> timestampTimezoneComboBox;
    private JTextArea timestampTextArea;
    private JSpinner timestampSpinner;
    private JButton timestampNowButton;
    private JButton timestampUpdateFromTimestampButton;
    private JButton timestampUpdateFromFieldsButton;
    private JLabel timestampWarningNoZoneIdLabel;
    private JSpinner timestampYearSpinner;
    private JSpinner timestampDaySpinner;
    private JSpinner timestampMonthSpinner;
    private JSpinner timestampHourSpinner;
    private JSpinner timestampMinuteSpinner;
    private JSpinner timestampSecondSpinner;
    private JSpinner timestampMillisecondSpinner;
    private JComboBox<String> timestampResolutionComboBox;
    private JLabel timestampMillisecondLabel;

    private JPanel dataFakerPanel;
    private JComboBox<String> dataFakerGeneratorComboBox;
    private JButton dataFakerGenerateButton;
    private JComboBox<String> dataFakerLocaleComboBox;
    private JTextArea dataFakerTextArea;

    private JPanel linesUtilsPanel;
    private JComboBox<String> linesUtilsComboBox;
    private JButton linesUtilsCompareButton;
    private JCheckBox linesUtilsCaseSensitiveCheckBox;
    private JTextArea linesUtilsTextArea1;
    private JTextArea linesUtilsTextArea2;
    private JTextArea linesUtilsResultTextArea;
    private JCheckBox linesUtilsIgnoreEmptyLinesCheckBox;

    private JPanel asciihexPanel;
    private JTextArea asciihexASCIITextArea;
    private JTextArea asciihexHEXTextArea;
    private JCheckBox asciihexSpacesCheckBox;

    private JPanel escapePanel;
    private JComboBox<String> escapeComboBox;
    private JTextArea unescapedTextArea;
    private JTextArea escapedTextArea;

    private JPanel cronPanel;
    private JTextField cronExpressionTextField;
    private JSpinner cronExpressionHowManyDaysSpinner;
    private JComboBox<String> cronTypeComboBox;
    private JTextArea cronTextArea;
    private JButton explainButton;


    private JPanel propertiesConsoleLoggerPanel;
    private JTextArea propertiesConsoleLoggerTextArea1;
    private JTextArea propertiesConsoleLoggerTextArea2;
    private JTextArea propertiesConsoleLoggerTextArea3;
    private JTextArea propertiesConsoleLoggerTextArea4;
    private JTextArea propertiesConsoleLoggerTextArea5;
    private JTextArea propertiesConsoleLoggerTextArea6;
    private JTextArea propertiesConsoleLoggerTextArea7;
    private JTextArea propertiesConsoleLoggerTextArea8;
    private JTextArea propertiesConsoleLoggerTextArea9;

    private JPanel hmacPanel;
    private JComboBox<String> hmacAlgoComboBox;
    private JTextField hmacKeyTextField;
    private JTextArea hmacInputTextArea;
    private JTextField hmacResultTextField;
    private JPanel     passwordVerifierPanel;
    private JTextField passwordVerifierInputPassword;
    private JTextField passwordVerifierHashTextField;
    private JBTextField passwordVerifierResultLabel;
    private JButton hashItButton;

    private final LinkedHashMap<String, PanelAndIcon> toolPanelsByTitle = new LinkedHashMap<>();

    private record PanelAndIcon(JPanel panel, String icon) {
    }

    public DevToysToolWindow() {
        String iconsPath = "bgdevtoys/toolicons/";
        toolPanelsByTitle.put("Properties of ConsoleLogger ", new PanelAndIcon(propertiesConsoleLoggerPanel, iconsPath + "PropertiesYaml.svg"));
        toolPanelsByTitle.put("Base64 encoder/decoder", new PanelAndIcon(base64Panel, iconsPath + "Base64EncoderDecoder.svg"));
        toolPanelsByTitle.put("URL encoder/decoder", new PanelAndIcon(urlCodecPanel, iconsPath + "UrlEncoderDecoder.svg"));
        toolPanelsByTitle.put("Fake Data generator", new PanelAndIcon(dataFakerPanel, iconsPath + "DataFaker.svg"));
        toolPanelsByTitle.put("Timestamp converter", new PanelAndIcon(timestampPanel, iconsPath + "Timestamp.svg"));
        toolPanelsByTitle.put("Cron parser", new PanelAndIcon(cronPanel, iconsPath + "CronParser.svg"));
        toolPanelsByTitle.put("Lines utils", new PanelAndIcon(linesUtilsPanel, iconsPath + "SetDiff.svg"));
        toolPanelsByTitle.put("Lorem Ipsum generator", new PanelAndIcon(loremIpsumPanel, iconsPath + "LoremIpsumGenerator.svg"));
        toolPanelsByTitle.put("Hash generator", new PanelAndIcon(hashPanel, iconsPath + "HashGenerator.svg"));
        toolPanelsByTitle.put("HMAC generator", new PanelAndIcon(hmacPanel, iconsPath + "HMACGenerator.svg"));
        toolPanelsByTitle.put("UUID generator", new PanelAndIcon(uuidPanel, iconsPath + "UuidGenerator.svg"));
        toolPanelsByTitle.put("Password strength evaluator", new PanelAndIcon(passwordStrengthPanel, iconsPath + "PasswordStrengthEvaluator.svg"));
        toolPanelsByTitle.put("Password hash verifier", new PanelAndIcon(passwordVerifierPanel, iconsPath + "PasswordHashVerifier.svg"));
        toolPanelsByTitle.put("Text escape/unescape", new PanelAndIcon(escapePanel, iconsPath + "Escaper.svg"));
        toolPanelsByTitle.put("ASCII <> HEX converter", new PanelAndIcon(asciihexPanel, iconsPath + "AsciiHex.svg"));

        new PropertiesConsoleLoggerToolSetup(
                propertiesConsoleLoggerTextArea1,
                propertiesConsoleLoggerTextArea2,
                propertiesConsoleLoggerTextArea3,
                propertiesConsoleLoggerTextArea4,
                propertiesConsoleLoggerTextArea5,
                propertiesConsoleLoggerTextArea6,
                propertiesConsoleLoggerTextArea7,
                propertiesConsoleLoggerTextArea8,
                propertiesConsoleLoggerTextArea9).setup();
        new Base64ToolSetup(
            base64RadioButtonUTF8,
            base64RadioButtonASCII,
            base64RawTextArea,
            base64Base64TextArea).setup();
        new URLCodecToolSetup(
            urlCodecDecodedTextField,
            urlCodecEncodedTextField).setup();
        new DataFakerToolSetup(
            dataFakerGeneratorComboBox,
            dataFakerGenerateButton,
            dataFakerLocaleComboBox,
            dataFakerTextArea).setup();
        new TimestampToolSetup(
            timestampTimezoneComboBox,
            timestampTextArea,
            timestampSpinner,
            timestampNowButton,
            timestampUpdateFromTimestampButton,
            timestampUpdateFromFieldsButton,
            timestampWarningNoZoneIdLabel,
            timestampYearSpinner,
            timestampDaySpinner,
            timestampMonthSpinner,
            timestampHourSpinner,
            timestampMinuteSpinner,
            timestampSecondSpinner,
            timestampMillisecondSpinner,
            timestampResolutionComboBox,
            timestampMillisecondLabel).setup();
        new CronToolSetup(
            cronExpressionTextField,
            cronExpressionHowManyDaysSpinner,
            cronTypeComboBox,
            cronTextArea,
            explainButton).setup();
        LinesUtilsToolSetup linesUtilsToolSetup = new LinesUtilsToolSetup(
            helpLabel,
            linesUtilsComboBox,
            linesUtilsCompareButton,
            linesUtilsCaseSensitiveCheckBox,
            linesUtilsTextArea1,
            linesUtilsTextArea2,
            linesUtilsResultTextArea,
            linesUtilsIgnoreEmptyLinesCheckBox);
        linesUtilsToolSetup.setup();
        new LoremIpsumToolSetup(
            loremIpsumGenerateButton,
            loremIpsumTextArea).setup();
        var hashToolSetup = new HashToolSetup(
            hashInputTextArea,
            hashMD5TextField,
            hashSHA1TextField,
            hashSHA256TextField,
            hashSHA384TextField,
            hashSHA512TextField,
            hashBCrypt2ATextField,
            hashBCrypt2BTextField,
            hashBCrypt2YTextField);
        hashToolSetup.setup();
        new PasswordVerifierToolSetup(
            passwordVerifierHashTextField,
            passwordVerifierInputPassword,
            passwordVerifierResultLabel
        ).setup();
        new UUIDToolSetup(
            uuidGenerateButton,
            uuidTextArea).setup();
        new ASCIIHEXToolSetup(
            asciihexASCIITextArea,
            asciihexHEXTextArea,
            asciihexSpacesCheckBox).setup();
        new EscapeToolSetup(
            escapeComboBox,
            unescapedTextArea,
            escapedTextArea).setup();
        new PasswordStrengthToolSetup(
            passwordStrengthPasswordTextField,
            passwordStrengthReportTextArea,
            hashItButton,
            hashInputTextArea,
            e -> {
                hashInputTextArea.setText(passwordStrengthPasswordTextField.getText());
                selectToolByName("Hash generator");
                hashToolSetup.update();
            }).setup();
        new HMACToolSetup(
            hmacAlgoComboBox,
            hmacKeyTextField,
            hmacInputTextArea,
            hmacResultTextField).setup();

        toolPanelsByTitle.forEach((title, panelAndIcon) -> toolComboBox.addItem(new ComboBoxWithImageItem(title, panelAndIcon.icon)));
        toolComboBox.setRenderer(new ComboBoxWithImageRenderer());
        toolComboBox.setMaximumRowCount(11);
        ComboboxSpeedSearch.installSpeedSearch(toolComboBox, ComboBoxWithImageItem::displayName);

        helpLabel.setText("");
        helpLabel.setIcon(IconLoader.getIcon(iconsPath + "contextHelp.svg", DevToysToolWindow.class));
        helpLabel.setToolTipText("");
        helpLabel.setVisible(false);

        toolComboBox.addActionListener(e -> {
            ComboBoxWithImageItem item = toolComboBox.getItemAt(toolComboBox.getSelectedIndex());
            displayToolPanel(item.title());

            helpLabel.setVisible(false);
            switch (item.title()) {
                case "Base64 encoder/decoder" -> {
                    helpLabel.setVisible(true);
                    helpLabel.setToolTipText("<html>" +
                        "Type some text or Base64 and it will be<br>" +
                        "automatically converted as you type.</html>");
                }
                case "URL encoder/decoder" -> {
                    helpLabel.setVisible(true);
                    helpLabel.setToolTipText("<html>" +
                        "Type decoded or encoded URL and it will be<br>" +
                        "automatically converted as you type.</html>");
                }
                case "Hash generator" -> {
                    helpLabel.setVisible(true);
                    helpLabel.setToolTipText("<html>" +
                        "Type text and various hash values will<br>" +
                        "be automatically computed as you type.</html>");
                }
                case "Password hash verifier" -> {
                    helpLabel.setVisible(true);
                    helpLabel.setToolTipText("<html>Type a password and a hash<br>" +
                        "and the tool will say if the password<br>" +
                        "verifies the hash with an algorithm like MD5,<br>" +
                        "SHA1/256/384/512 or BCrypt 2A/2B/2Y.</html>");
                }
                case "Timestamp converter" -> {
                    helpLabel.setVisible(true);
                    helpLabel.setToolTipText("<html>" +
                        "Type a timestamp or update datetime field(s)<br>" +
                        "then hit the <i>Update from timestamp</i> or<br>" +
                        "<i>Update from fields</i> button.</html>");
                }
                case "Lines utils" -> {
                    linesUtilsToolSetup.activate();
                }
            }
        });
        toolComboBox.setSelectedIndex(0);
    }

    private void displayToolPanel(String toolPanelTitle) {
        toolPanelsByTitle.forEach((s, jPanel) -> jPanel.panel().setVisible(false));
        toolPanelsByTitle.get(toolPanelTitle).panel().setVisible(true);
    }

    private void selectToolByName(String toolName) {
        for (int i = 0; i < toolComboBox.getItemCount(); i++) {
            if (toolComboBox.getItemAt(i).title().equals(toolName)) {
                toolComboBox.setSelectedIndex(i);
                break;
            }
        }
    }

    public JPanel getContent() {
        return mainPanel;
    }
}
