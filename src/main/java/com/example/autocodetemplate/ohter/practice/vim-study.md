## vim的优势

- vim编辑文件高效
- 跨平台
- 高度可定制，可扩展
- 良好生态环境，大量现成脚本和插件

## vim常用模式

- 普通模式：vim启动后默认模式，光标，文本操作
- 插入模式：i/a进入插入模式，这个模式敲击键盘会往文字缓冲区增加文字
- 可视模式：文本选择，以及之后的复制，删除，排序操作
- 命令模式：通过:进入，执行内部命令，向上/向下搜索，执行外部命令

## vim的选项和配置

vim配置文件在用户主目录下，文件名为.vimrc

vim配置大全

```Java
// 设置编辑文件的内码为UTF-8
set enc=utf-8 

// 设置vim不需要和vi兼容
set nocompatible

// 每次编辑保留上一次备份文件
set backup

//vim重新打开时，仍然能撤销之前的编辑（形成一个undofile）
set undofile

// 设置undofile文件存放目录
set undodir=~/.vim/undodir
```