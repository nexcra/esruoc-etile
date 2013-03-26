/*
Copyright (c) 2003-2011, CKSource - Frederico Knabben. All rights reserved.
For licensing, see LICENSE.html or http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function(config) {
	config.language = 'zh-cn'; // 配置语言
	config.uiColor = '#FFF'; // 背景颜色
	config.width = 'auto'; // 宽度
	config.height = '300px'; // 高度
	config.skin = 'office2003';// 界面v2,kama,office2003
	config.toolbar = 'Full';// 工具栏风格Full,Basic

	config.tabSpaces = 4;
	config.resize_maxWidth = 800; // 如果设置了编辑器可以拖拽这是可以移动的最大宽度
	config.toolbarCanCollapse = true; // 工具栏可以隐藏
	// config.toolbarLocation = 'bottom'; //可选：设置工具栏在整个编辑器的底部bottom
	config.resize_minWidth = 600; // 如果设置了编辑器可以拖拽这是可以移动的最小宽度
	config.resize_enabled = true; // 如果设置了编辑器可以拖拽
	config.font_names = ' 宋体/宋体;黑体/黑体;仿宋/仿宋_GB2312;楷体/楷体_GB2312;隶书/隶书;幼圆/幼圆;微软雅黑/微软雅黑;'
			+ config.font_names;
	config.fontSize_defaultLabel = '14px'; 
	// 下面是编辑器工具栏配置，"/"代表换行
	config.toolbar_Full = [
			[ 'Source'/* , '-', 'Save' , 'NewPage', */, 'Preview', '-'/*
																		 * ,
																		 * 'Templates'
																		 */],
			[ 'Cut', 'Copy', 'Paste', 'PasteText', 'PasteFromWord', /*
																	 * '-',
																	 * 'Print',
																	 * 'SpellChecker',
																	 * 'Scayt'
																	 */],
			[ 'Undo', 'Redo', '-', 'Find', 'Replace', '-', 'SelectAll',
					'RemoveFormat' ],
			[ 'Form', 'Checkbox', 'Radio', 'TextField', 'Textarea', 'Select',
					'Button', 'ImageButton', 'HiddenField' ],
			'/',
			[ 'Bold', 'Italic', 'Underline', 'Strike', '-', 'Subscript',
					'Superscript' ],
			[ 'NumberedList', 'BulletedList', '-', 'Outdent', 'Indent',
					'Blockquote' ],
			[ 'JustifyLeft', 'JustifyCenter', 'JustifyRight', 'JustifyBlock' ],
			[ 'Link', 'Unlink', 'Anchor' ],
			[ 'Image', 'Flash', 'Table', 'HorizontalRule', 'Smiley',
					'SpecialChar', 'PageBreak' ], '/',
			[ 'Styles', 'Format', 'Font', 'FontSize' ],
			[ 'TextColor', 'BGColor' ] ];

};
